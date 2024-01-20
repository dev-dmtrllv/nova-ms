/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

// import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import tools.PacketCreator;

public class RipCommand extends Command {
    {
        setDescription("Send a RIP notice.");
    }

    @Override
    public void execute(Client c, String[] params) {
        // Character player = c.getPlayer();
        Server.getInstance().broadcastMessage(c.getWorld(), PacketCreator.serverNotice(6, "[RIP]: " + joinStringFrom(params, 1)));
    }
}
