/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.channel.Channel;

public class OnlineTwoCommand extends Command {
    {
        setDescription("Show all online players.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int total = 0;
        for (Channel ch : Server.getInstance().getChannelsFromWorld(player.getWorld())) {
            int size = ch.getPlayerStorage().getAllCharacters().size();
            total += size;
            String s = "(Channel " + ch.getId() + " Online: " + size + ") : ";
            if (ch.getPlayerStorage().getAllCharacters().size() < 50) {
                for (Character chr : ch.getPlayerStorage().getAllCharacters()) {
                    s += Character.makeMapleReadable(chr.getName()) + ", ";
                }
                player.dropMessage(6, s.substring(0, s.length() - 2));
            }
        }

        //player.dropMessage(6, "There are a total of " + total + " players online.");
        player.showHint("Players online: #e#r" + total + "#k#n.", 300);
    }
}
