/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MapId;
import server.maps.MapleMap;
import server.maps.Portal;

import static java.util.concurrent.TimeUnit.MINUTES;

public class JailCommand extends Command {
    {
        setDescription("Move a player to the jail.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !jail <playername> [<minutes>]");
            return;
        }

        int minutesJailed = 5;
        if (params.length >= 2) {
            minutesJailed = Integer.parseInt(params[1]);
            if (minutesJailed <= 0) {
                player.yellowMessage("Syntax: !jail <playername> [<minutes>]");
                return;
            }
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.addJailExpirationTime(MINUTES.toMillis(minutesJailed));

            if (victim.getMapId() != MapId.JAIL) {    // those gone to jail won't be changing map anyway
                MapleMap target = c.getChannelServer().getMapFactory().getMap(MapId.JAIL);
                Portal targetPortal = target.getPortal(0);
                victim.saveLocationOnWarp();
                victim.changeMap(target, targetPortal);
                player.message(victim.getName() + " was jailed for " + minutesJailed + " minutes.");
            } else {
                player.message(victim.getName() + "'s time in jail has been extended for " + minutesJailed + " minutes.");
            }

        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
