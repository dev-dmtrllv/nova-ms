/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import config.YamlConfig;

public class LevelCommand extends Command {
    {
        setDescription("Set your level.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !level <newlevel>");
            return;
        }

        player.loseExp(player.getExp(), false, false);
        player.setLevel(Math.min(Integer.parseInt(params[0]), player.getMaxClassLevel()) - 1);

        player.resetPlayerRates();
        if (YamlConfig.config.server.USE_ADD_RATES_BY_LEVEL) {
            player.setPlayerRates();
        }
        player.setWorldRates();

        player.levelUp(false);
    }
}
