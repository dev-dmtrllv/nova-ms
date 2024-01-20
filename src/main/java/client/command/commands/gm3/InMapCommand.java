/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class InMapCommand extends Command {
    {
        setDescription("Show all players in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        String st = "";
        for (Character chr : player.getMap().getCharacters()) {
            st += chr.getName() + " ";
        }
        player.message(st);

    }
}
