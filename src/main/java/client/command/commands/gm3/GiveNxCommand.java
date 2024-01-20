/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class GiveNxCommand extends Command {
    {
        setDescription("Give NX to a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !givenx [nx, mp, np] [<playername>] <gainnx>");
            return;
        }

        String recv, typeStr = "nx";
        int value, type = 1;
        if (params.length > 1) {
            if (params[0].length() == 2) {
                switch (params[0]) {
                    case "mp":  // maplePoint
                        type = 2;
                        break;
                    case "np":  // nxPrepaid
                        type = 4;
                        break;
                    default:
                        type = 1;
                }
                typeStr = params[0];

                if (params.length > 2) {
                    recv = params[1];
                    value = Integer.parseInt(params[2]);
                } else {
                    recv = c.getPlayer().getName();
                    value = Integer.parseInt(params[1]);
                }
            } else {
                recv = params[0];
                value = Integer.parseInt(params[1]);
            }
        } else {
            recv = c.getPlayer().getName();
            value = Integer.parseInt(params[0]);
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(recv);
        if (victim != null) {
            victim.getCashShop().gainCash(type, value);
            player.message(typeStr.toUpperCase() + " given.");
        } else {
            player.message("Player '" + recv + "' could not be found.");
        }
    }
}
