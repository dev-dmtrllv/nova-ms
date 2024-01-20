/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm5;

import client.Client;
import client.command.Command;
import config.YamlConfig;

public class ShowPacketsCommand extends Command {
    {
        setDescription("Toggle show received packets in console.");
    }

    @Override
    public void execute(Client c, String[] params) {
        YamlConfig.config.server.USE_DEBUG_SHOW_RCVD_PACKET = !YamlConfig.config.server.USE_DEBUG_SHOW_RCVD_PACKET;
    }
}
