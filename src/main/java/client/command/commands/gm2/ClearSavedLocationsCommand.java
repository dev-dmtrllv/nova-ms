/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.SavedLocationType;

public class ClearSavedLocationsCommand extends Command {
    {
        setDescription("Clear saved locations for a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer(), victim;

        if (params.length > 0) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            if (victim == null) {
                player.message("Player '" + params[0] + "' could not be found.");
                return;
            }
        } else {
            victim = c.getPlayer();
        }

        for (SavedLocationType type : SavedLocationType.values()) {
            victim.clearSavedLocation(type);
        }

        player.message("Cleared " + params[0] + "'s saved locations.");
    }
}
