/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.quest.Quest;

public class QuestCompleteCommand extends Command {
    {
        setDescription("Complete an active quest.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        if (params.length < 1) {
            player.yellowMessage("Syntax: !completequest <questid>");
            return;
        }

        int questId = Integer.parseInt(params[0]);

        if (player.getQuestStatus(questId) == 1) {
            Quest quest = Quest.getInstance(questId);
            if (quest != null && quest.getNpcRequirement(true) != -1) {
                c.getAbstractPlayerInteraction().forceCompleteQuest(questId, quest.getNpcRequirement(true));
            } else {
                c.getAbstractPlayerInteraction().forceCompleteQuest(questId);
            }

            player.dropMessage(5, "QUEST " + questId + " completed.");
        } else {
            player.dropMessage(5, "QUESTID " + questId + " not started or already completed.");
        }
    }
}
