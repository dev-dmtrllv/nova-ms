/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import server.ThreadManager;

public class ServerRemoveWorldCommand extends Command {
    {
        setDescription("Remove a world.");
    }

    @Override
    public void execute(Client c, String[] params) {
        final Character player = c.getPlayer();

        final int rwid = Server.getInstance().getWorldsSize() - 1;
        if (rwid <= 0) {
            player.dropMessage(5, "Unable to remove world 0.");
            return;
        }

        ThreadManager.getInstance().newTask(() -> {
            if (Server.getInstance().removeWorld()) {
                if (player.isLoggedinWorld()) {
                    player.dropMessage(5, "Successfully removed a world. Current world count: " + Server.getInstance().getWorldsSize() + ".");
                }
            } else {
                if (player.isLoggedinWorld()) {
                    if (rwid < 0) {
                        player.dropMessage(5, "No registered worlds to remove.");
                    } else {
                        player.dropMessage(5, "Failed to remove world " + rwid + ". Check if there are people currently playing there.");
                    }
                }
            }
        });
    }
}
