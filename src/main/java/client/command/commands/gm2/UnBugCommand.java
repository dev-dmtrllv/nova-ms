/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class UnBugCommand extends Command {
    {
        setDescription("Unbug self.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getPlayer().getMap().broadcastMessage(PacketCreator.enableActions());
    }
}
