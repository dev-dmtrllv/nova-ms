/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;
import config.YamlConfig;

public class DropLimitCommand extends Command {
    {
        setDescription("Check drop limit of current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        int dropCount = c.getPlayer().getMap().getDroppedItemCount();
        if (((float) dropCount) / YamlConfig.config.server.ITEM_LIMIT_ON_MAP < 0.75f) {
            c.getPlayer().showHint("Current drop count: #b" + dropCount + "#k / #e" + YamlConfig.config.server.ITEM_LIMIT_ON_MAP + "#n", 300);
        } else {
            c.getPlayer().showHint("Current drop count: #r" + dropCount + "#k / #e" + YamlConfig.config.server.ITEM_LIMIT_ON_MAP + "#n", 300);
        }

    }
}
