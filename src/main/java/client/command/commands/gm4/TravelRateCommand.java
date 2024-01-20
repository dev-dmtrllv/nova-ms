/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class TravelRateCommand extends Command {
    {
        setDescription("Set world travel rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !travelrate <newrate>");
            return;
        }

        int travelrate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setTravelRate(travelrate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Travel Rate has been changed to " + travelrate + "x."));
    }
}
