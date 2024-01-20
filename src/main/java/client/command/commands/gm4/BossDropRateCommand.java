package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;
/**
 * @author Ronan
 */
public class BossDropRateCommand extends Command {
    {
        setDescription("Set world boss drop rate.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !bossdroprate <newrate>");
            return;
        }

        int bossdroprate = Math.max(Integer.parseInt(params[0]), 1);
        c.getWorldServer().setBossDropRate(bossdroprate);
        c.getWorldServer().broadcastPacket(PacketCreator.serverNotice(6, "[Rate] Boss Drop Rate has been changed to " + bossdroprate + "x."));
    }
}
