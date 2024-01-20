/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;

public class LeaveEventCommand extends Command {
    {
        setDescription("Leave active event.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int returnMap = player.getSavedLocation("EVENT");
        if (returnMap != -1) {
            if (player.getOla() != null) {
                player.getOla().resetTimes();
                player.setOla(null);
            }
            if (player.getFitness() != null) {
                player.getFitness().resetTimes();
                player.setFitness(null);
            }

            player.saveLocationOnWarp();
            player.changeMap(returnMap);
            if (c.getChannelServer().getEvent() != null) {
                c.getChannelServer().getEvent().addLimit();
            }
        } else {
            player.dropMessage(5, "You are not currently in an event.");
        }

    }
}
