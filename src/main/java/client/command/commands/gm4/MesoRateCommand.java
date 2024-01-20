/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class MesoRateCommand extends Command {
    {
        setDescription("Set world meso rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !mesorate <newrate>");
            return;
        }

        int mesorate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setMesoRate(mesorate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Meso Rate has been changed to " + mesorate + "x."));
    }
}
