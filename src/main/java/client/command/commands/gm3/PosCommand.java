/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class PosCommand extends Command {
    {
        setDescription("Show current position and foothold.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        float xpos = player.getPosition().x;
        float ypos = player.getPosition().y;
        float fh = player.getMap().getFootholds().findBelow(player.getPosition()).getId();
        player.dropMessage(6, "Position: (" + xpos + ", " + ypos + ")");
        player.dropMessage(6, "Foothold ID: " + fh);
    }
}
