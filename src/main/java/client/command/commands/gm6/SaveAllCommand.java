/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.world.World;
import tools.PacketCreator;

public class SaveAllCommand extends Command {
    {
        setDescription("Save all characters.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (World world : Server.getInstance().getWorlds()) {
            for (Character chr : world.getPlayerStorage().getAllCharacters()) {
                chr.saveCharToDB();
            }
        }
        String message = player.getName() + " used !saveall.";
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(5, message));
        player.message("All players saved successfully.");
    }
}
