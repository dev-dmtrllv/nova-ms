/*
   @Author: MedicOP - Add clock commands
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class TimerMapCommand extends Command {
    {
        setDescription("Set timer on all players in current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !timermap <seconds>|remove");
            return;
        }

        if (params[0].equalsIgnoreCase("remove")) {
            for (Character victim : player.getMap().getCharacters()) {
                victim.sendPacket(PacketCreator.removeClock());
            }
        } else {
            try {
                int seconds = Integer.parseInt(params[0]);
                for (Character victim : player.getMap().getCharacters()) {
                    victim.sendPacket(PacketCreator.getClock(seconds));
                }
            } catch (NumberFormatException e) {
                player.yellowMessage("Syntax: !timermap <seconds>|remove");
            }
        }
    }
}
