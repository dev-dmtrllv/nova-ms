/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class SetStatCommand extends Command {
    {
        setDescription("Set all primary stats to new value.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !setstat <newstat>");
            return;
        }

        try {
            int x = Integer.parseInt(params[0]);

            if (x > Short.MAX_VALUE) {
                x = Short.MAX_VALUE;
            } else if (x < 4) {
                x = 4;  // thanks Vcoc for pointing the minimal allowed stat value here
            }

            player.updateStrDexIntLuk(x);
        } catch (NumberFormatException nfe) {
        }
    }
}
