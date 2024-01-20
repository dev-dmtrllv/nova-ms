/*
   @Author: MedicOP - Add clock commands
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class TimerAllCommand extends Command {
    {
        setDescription("Set a server wide timer.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !timerall <seconds>|remove");
            return;
        }

        if (params[0].equalsIgnoreCase("remove")) {
            for (Character victim : player.getWorldServer().getPlayerStorage().getAllCharacters()) {
                victim.sendPacket(PacketCreator.removeClock());
            }
        } else {
            try {
                int seconds = Integer.parseInt(params[0]);
                for (Character victim : player.getWorldServer().getPlayerStorage().getAllCharacters()) {
                    victim.sendPacket(PacketCreator.getClock(seconds));
                }
            } catch (NumberFormatException e) {
                player.yellowMessage("Syntax: !timerall <seconds>|remove");
            }
        }
    }
}
