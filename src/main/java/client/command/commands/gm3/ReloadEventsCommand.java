/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.channel.Channel;

public class ReloadEventsCommand extends Command {
    {
        setDescription("Reload all event data.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Channel ch : Server.getInstance().getAllChannels()) {
            ch.reloadEventScriptManager();
        }
        player.dropMessage(5, "Reloaded Events");
    }
}
