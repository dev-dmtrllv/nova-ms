/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import config.YamlConfig;

public class ApCommand extends Command {
    {
        setDescription("Set available AP.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !ap [<playername>] <newap>");
            return;
        }

        if (params.length < 2) {
            int newAp = Integer.parseInt(params[0]);
            if (newAp < 0) {
                newAp = 0;
            } else if (newAp > YamlConfig.config.server.MAX_AP) {
                newAp = YamlConfig.config.server.MAX_AP;
            }

            player.changeRemainingAp(newAp, false);
        } else {
            Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            if (victim != null) {
                int newAp = Integer.parseInt(params[1]);
                if (newAp < 0) {
                    newAp = 0;
                } else if (newAp > YamlConfig.config.server.MAX_AP) {
                    newAp = YamlConfig.config.server.MAX_AP;
                }

                victim.changeRemainingAp(newAp, false);
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        }
    }
}
