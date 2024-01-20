/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import config.YamlConfig;

public class SpCommand extends Command {
    {
        setDescription("Set available SP.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !sp [<playername>] <newsp>");
            return;
        }

        if (params.length == 1) {
            int newSp = Integer.parseInt(params[0]);
            if (newSp < 0) {
                newSp = 0;
            } else if (newSp > YamlConfig.config.server.MAX_AP) {
                newSp = YamlConfig.config.server.MAX_AP;
            }

            player.updateRemainingSp(newSp);
        } else {
            Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            if (victim != null) {
                int newSp = Integer.parseInt(params[1]);
                if (newSp < 0) {
                    newSp = 0;
                } else if (newSp > YamlConfig.config.server.MAX_AP) {
                    newSp = YamlConfig.config.server.MAX_AP;
                }

                victim.updateRemainingSp(newSp);

                player.dropMessage(5, "SP given.");
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        }
    }
}
