/*
   @Author: Ronan
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class FishingRateCommand extends Command {
    {
        setDescription("Set fishing rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !fishrate <newrate>");
            return;
        }

        int fishrate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setFishingRate(fishrate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Fishing Rate has been changed to " + fishrate + "x."));
    }
}
