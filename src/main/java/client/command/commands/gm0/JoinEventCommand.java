/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MapId;
import server.events.gm.Event;
import server.maps.FieldLimit;

public class JoinEventCommand extends Command {
    {
        setDescription("Join active event.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (!FieldLimit.CANNOTMIGRATE.check(player.getMap().getFieldLimit())) {
            Event event = c.getChannelServer().getEvent();
            if (event != null) {
                if (event.getMapId() != player.getMapId()) {
                    if (event.getLimit() > 0) {
                        player.saveLocation("EVENT");

                        if (event.getMapId() == MapId.EVENT_COCONUT_HARVEST || event.getMapId() == MapId.EVENT_SNOWBALL_ENTRANCE) {
                            player.setTeam(event.getLimit() % 2);
                        }

                        event.minusLimit();

                        player.saveLocationOnWarp();
                        player.changeMap(event.getMapId());
                    } else {
                        player.dropMessage(5, "The limit of players for the event has already been reached.");
                    }
                } else {
                    player.dropMessage(5, "You are already in the event.");
                }
            } else {
                player.dropMessage(5, "There is currently no event in progress.");
            }
        } else {
            player.dropMessage(5, "You are currently in a map where you can't join an event.");
        }
    }
}
