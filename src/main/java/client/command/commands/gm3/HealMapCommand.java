/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class HealMapCommand extends Command {
    {
        setDescription("Heal all HP/MP of all players in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Character mch : player.getMap().getCharacters()) {
            if (mch != null) {
                mch.healHpMp();
            }
        }
    }
}
