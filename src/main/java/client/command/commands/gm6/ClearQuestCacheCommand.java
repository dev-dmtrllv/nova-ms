/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import server.quest.Quest;

public class ClearQuestCacheCommand extends Command {
    {
        setDescription("Clear all quest cache.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Quest.clearCache();
        player.dropMessage(5, "Quest Cache Cleared.");
    }
}
