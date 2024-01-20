/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.world.World;

public class DCAllCommand extends Command {
    {
        setDescription("Disconnect all players (online or logged in).");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (World world : Server.getInstance().getWorlds()) {
            for (Character chr : world.getPlayerStorage().getAllCharacters()) {
                if (!chr.isGM()) {
                    chr.getClient().disconnect(false, false);
                }
            }
        }
        player.message("All players successfully disconnected.");
    }
}
