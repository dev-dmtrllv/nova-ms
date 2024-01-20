/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class ExpRateCommand extends Command {
    {
        setDescription("Set world exp rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !exprate <newrate>");
            return;
        }

        int exprate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setExpRate(exprate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Exp Rate has been changed to " + exprate + "x."));
    }
}
