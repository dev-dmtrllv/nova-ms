/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.PacketCreator;

public class ReportBugCommand extends Command {
    {
        setDescription("Send in a bug report.");
    }

    private static final Logger log = LoggerFactory.getLogger(ReportBugCommand.class);

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        if (params.length < 1) {
            player.dropMessage(5, "Message too short and not sent. Please do @bug <bug>");
            return;
        }
        String message = player.getLastCommandMessage();
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.sendYellowTip("[Bug]:" + Character.makeMapleReadable(player.getName()) + ": " + message));
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(1, message));
        log.info("{}: {}", Character.makeMapleReadable(player.getName()), message);
        player.dropMessage(5, "Your bug '" + message + "' was submitted successfully to our developers. Thank you!");

    }
}
