/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.autoban.AutobanFactory;
import client.command.Command;
import net.server.Server;
import tools.PacketCreator;

public class IgnoreCommand extends Command {
    {
        setDescription("Toggle ignore a character from auto-ban alerts.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !ignore <ign>");
            return;
        }
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim == null) {
            player.message("Player '" + params[0] + "' could not be found on this world.");
            return;
        }

        boolean ignored = AutobanFactory.toggleIgnored(victim.getId());
        player.yellowMessage(victim.getName() + " is " + (ignored ? "now being ignored." : "no longer being ignored."));
        String message_ = player.getName() + (ignored ? " has started ignoring " : " has stopped ignoring ") + victim.getName() + ".";
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(5, message_));

    }
}
