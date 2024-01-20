/*
   @Author: MedicOP - Add clock commands
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class TimerCommand extends Command {
    {
        setDescription("Set timer on a player in current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !timer <playername> <seconds>|remove");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            if (params[1].equalsIgnoreCase("remove")) {
                victim.sendPacket(PacketCreator.removeClock());
            } else {
                try {
                    victim.sendPacket(PacketCreator.getClock(Integer.parseInt(params[1])));
                } catch (NumberFormatException e) {
                    player.yellowMessage("Syntax: !timer <playername> <seconds>|remove");
                }
            }
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
