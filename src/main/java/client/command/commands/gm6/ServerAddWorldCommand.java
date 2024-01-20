/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import server.ThreadManager;

public class ServerAddWorldCommand extends Command {
    {
        setDescription("Add a new world.");
    }

    @Override
    public void execute(Client c, String[] params) {
        final Character player = c.getPlayer();

        ThreadManager.getInstance().newTask(() -> {
            int wid = Server.getInstance().addWorld();

            if (player.isLoggedinWorld()) {
                if (wid >= 0) {
                    player.dropMessage(5, "NEW World " + wid + " successfully deployed.");
                } else {
                    if (wid == -2) {
                        player.dropMessage(5, "Error detected when loading the 'world.ini' file. World creation aborted.");
                    } else {
                        player.dropMessage(5, "NEW World failed to be deployed. Check if needed ports are already in use or maximum world count has been reached.");
                    }
                }
            }
        });
    }
}
