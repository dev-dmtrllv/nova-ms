/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.Stat;
import client.command.Command;
import config.YamlConfig;

public class MaxStatCommand extends Command {
    {
        setDescription("Max out all character stats.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.loseExp(player.getExp(), false, false);
        player.setLevel(255);
        player.resetPlayerRates();
        if (YamlConfig.config.server.USE_ADD_RATES_BY_LEVEL) {
            player.setPlayerRates();
        }
        player.setWorldRates();
        player.updateStrDexIntLuk(Short.MAX_VALUE);
        player.setFame(13337);
        player.updateMaxHpMaxMp(30000, 30000);
        player.updateSingleStat(Stat.LEVEL, 255);
        player.updateSingleStat(Stat.FAME, 13337);
        player.yellowMessage("Stats maxed out.");
    }
}
