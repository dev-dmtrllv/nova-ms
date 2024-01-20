/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class QuestRateCommand extends Command {
    {
        setDescription("Set world quest rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !questrate <newrate>");
            return;
        }

        int questrate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setQuestRate(questrate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Quest Rate has been changed to " + questrate + "x."));

    }
}
