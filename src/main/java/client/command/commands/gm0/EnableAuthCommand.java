/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;
import net.server.coordinator.login.LoginBypassCoordinator;

public class EnableAuthCommand extends Command {
    {
        setDescription("Enable PIC code by resetting the cooldown.");
    }

    @Override
    public void execute(Client c, String[] params) {
        if (c.tryacquireClient()) {
            try {
                LoginBypassCoordinator.getInstance().unregisterLoginBypassEntry(c.getHwid(), c.getAccID());
            } finally {
                c.releaseClient();
            }
        }
    }
}
