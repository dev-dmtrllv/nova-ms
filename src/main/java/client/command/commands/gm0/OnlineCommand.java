/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.channel.Channel;

public class OnlineCommand extends Command {
    {
        setDescription("Show all online players.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Channel ch : Server.getInstance().getChannelsFromWorld(player.getWorld())) {
            player.yellowMessage("Players in Channel " + ch.getId() + ":");
            for (Character chr : ch.getPlayerStorage().getAllCharacters()) {
                if (!chr.isGM()) {
                    player.message(" >> " + Character.makeMapleReadable(chr.getName()) + " is at " + chr.getMap().getMapName() + ".");
                }
            }
        }
    }
}
