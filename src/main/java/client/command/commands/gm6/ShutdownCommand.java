/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.world.World;
import server.TimerManager;

import static java.util.concurrent.TimeUnit.*;

public class ShutdownCommand extends Command {
    {
        setDescription("Shut down the server.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !shutdown [<time>|NOW]");
            return;
        }

        int time = 60000;
        if (params[0].equalsIgnoreCase("now")) {
            time = 1;
        } else {
            time *= Integer.parseInt(params[0]);
        }

        if (time > 1) {
            int seconds = (time / (int) SECONDS.toMillis(1)) % 60;
            int minutes = (time / (int) MINUTES.toMillis(1)) % 60;
            int hours = (time / (int) HOURS.toMillis(1)) % 24;
            int days = (time / (int) DAYS.toMillis(1));

            String strTime = "";
            if (days > 0) {
                strTime += days + " days, ";
            }
            if (hours > 0) {
                strTime += hours + " hours, ";
            }
            strTime += minutes + " minutes, ";
            strTime += seconds + " seconds";

            for (World w : Server.getInstance().getWorlds()) {
                for (Character chr : w.getPlayerStorage().getAllCharacters()) {
                    chr.dropMessage("Server is undergoing maintenance process, and will be shutdown in " + strTime + ". Prepare yourself to quit safely in the mean time.");
                }
            }
        }

        TimerManager.getInstance().schedule(Server.getInstance().shutdown(false), time);
    }
}
