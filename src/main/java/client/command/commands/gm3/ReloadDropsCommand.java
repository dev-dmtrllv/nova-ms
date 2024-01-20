/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.MonsterInformationProvider;

public class ReloadDropsCommand extends Command {
    {
        setDescription("Reload all drop data.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        MonsterInformationProvider.getInstance().clearDrops();
        player.dropMessage(5, "Reloaded Drops");
    }
}
