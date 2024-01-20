/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;
import net.server.Server;

import static java.util.concurrent.TimeUnit.*;

public class UptimeCommand extends Command {
    {
        setDescription("Show server online time.");
    }

    @Override
    public void execute(Client c, String[] params) {
        long milliseconds = System.currentTimeMillis() - Server.uptime;
        int seconds = (int) (milliseconds / SECONDS.toMillis(1)) % 60;
        int minutes = (int) ((milliseconds / MINUTES.toMillis(1)) % 60);
        int hours = (int) ((milliseconds / HOURS.toMillis(1)) % 24);
        int days = (int) ((milliseconds / DAYS.toMillis(1)));
        c.getPlayer().yellowMessage("Server has been online for " + days + " days " + hours + " hours " + minutes + " minutes and " + seconds + " seconds.");
    }
}
