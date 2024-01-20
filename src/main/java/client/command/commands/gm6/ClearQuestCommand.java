/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import server.quest.Quest;

public class ClearQuestCommand extends Command {
    {
        setDescription("Clear cache of a quest.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.dropMessage(5, "Please include a quest ID.");
            return;
        }
        Quest.clearCache(Integer.parseInt(params[0]));
        player.dropMessage(5, "Quest Cache for quest " + params[0] + " cleared.");

    }
}
