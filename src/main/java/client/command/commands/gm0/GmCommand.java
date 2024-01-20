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
import tools.Randomizer;

public class GmCommand extends Command {
    {
        setDescription("Send a message to the game masters.");
    }

    private static final Logger log = LoggerFactory.getLogger(GmCommand.class);

    @Override
    public void execute(Client c, String[] params) {
        String[] tips = {
                "Please only use @gm in emergencies or to report somebody.",
                "To report a bug or make a suggestion, use the forum.",
                "Please do not use @gm to ask if a GM is online.",
                "Do not ask if you can receive help, just state your issue.",
                "Do not say 'I have a bug to report', just state it.",
        };
        Character player = c.getPlayer();
        if (params.length < 1 || params[0].length() < 3) { // #goodbye 'hi'
            player.dropMessage(5, "Your message was too short. Please provide as much detail as possible.");
            return;
        }
        String message = player.getLastCommandMessage();
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.sendYellowTip("[GM Message]:" + Character.makeMapleReadable(player.getName()) + ": " + message));
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(1, message));
        log.info("{}: {}", Character.makeMapleReadable(player.getName()), message);
        player.dropMessage(5, "Your message '" + message + "' was sent to GMs.");
        player.dropMessage(5, tips[Randomizer.nextInt(tips.length)]);
    }
}
