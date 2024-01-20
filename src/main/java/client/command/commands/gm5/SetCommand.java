/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm5;

import client.Client;
import client.command.Command;
import constants.net.ServerConstants;

public class SetCommand extends Command {
    {
        setDescription("Store value in an array, for testing.");
    }

    @Override
    public void execute(Client c, String[] params) {
        for (int i = 0; i < params.length; i++) {
            ServerConstants.DEBUG_VALUES[i] = Integer.parseInt(params[i]);
        }
    }
}
