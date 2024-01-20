/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm5;

import client.Client;
import client.command.Command;
import config.YamlConfig;

public class ShowMoveLifeCommand extends Command {
    {
        setDescription("Toggle show move life in console.");
    }

    @Override
    public void execute(Client c, String[] params) {
        YamlConfig.config.server.USE_DEBUG_SHOW_RCVD_MVLIFE = !YamlConfig.config.server.USE_DEBUG_SHOW_RCVD_MVLIFE;
    }
}
