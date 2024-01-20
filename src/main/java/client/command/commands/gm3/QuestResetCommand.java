/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.quest.Quest;

public class QuestResetCommand extends Command {
    {
        setDescription("Reset a completed quest.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !resetquest <questid>");
            return;
        }

        int questid_ = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questid_) != 0) {
            Quest quest = Quest.getInstance(questid_);
            if (quest != null) {
                quest.reset(player);
                player.dropMessage(5, "QUEST " + questid_ + " reseted.");
            } else {    // should not occur
                player.dropMessage(5, "QUESTID " + questid_ + " is invalid.");
            }
        }
    }
}
