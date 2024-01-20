/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class DropRateCommand extends Command {
    {
        setDescription("Set world drop rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !droprate <newrate>");
            return;
        }

        int droprate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setDropRate(droprate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Drop Rate has been changed to " + droprate + "x."));

    }
}
