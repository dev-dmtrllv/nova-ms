/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class MuteMapCommand extends Command {
    {
        setDescription("Toggle mute players in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (player.getMap().isMuted()) {
            player.getMap().setMuted(false);
            player.dropMessage(5, "The map you are in has been un-muted.");
        } else {
            player.getMap().setMuted(true);
            player.dropMessage(5, "The map you are in has been muted.");
        }
    }
}
