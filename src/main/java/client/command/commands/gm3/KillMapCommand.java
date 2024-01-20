/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class KillMapCommand extends Command {
    {
        setDescription("Kill all players in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Character mch : player.getMap().getCharacters()) {
            mch.updateHp(0);
        }
    }
}
