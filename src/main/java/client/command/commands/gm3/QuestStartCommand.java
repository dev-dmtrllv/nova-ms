/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.quest.Quest;

public class QuestStartCommand extends Command {
    {
        setDescription("Start a quest.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !startquest <questid>");
            return;
        }

        int questid = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questid) == 0) {
            Quest quest = Quest.getInstance(questid);
            if (quest != null && quest.getNpcRequirement(false) != -1) {
                c.getAbstractPlayerInteraction().forceStartQuest(questid, quest.getNpcRequirement(false));
            } else {
                c.getAbstractPlayerInteraction().forceStartQuest(questid);
            }

            player.dropMessage(5, "QUEST " + questid + " started.");
        } else {
            player.dropMessage(5, "QUESTID " + questid + " already started/completed.");
        }
    }
}
