/*
   @Author: Ronan
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;

public class ToggleExpCommand extends Command {
    {
        setDescription("Toggle enable/disable all exp gain.");
    }

    @Override
    public void execute(Client c, String[] params) {
        if (c.tryacquireClient()) {
            try {
                c.getPlayer().toggleExpGain();  // Vcoc's idea
            } finally {
                c.releaseClient();
            }
        }
    }
}
