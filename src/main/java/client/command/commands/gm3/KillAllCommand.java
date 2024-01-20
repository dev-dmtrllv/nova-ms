/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import server.life.Monster;
import server.maps.MapObject;
import server.maps.MapObjectType;
import server.maps.MapleMap;

import java.util.Arrays;
import java.util.List;

public class KillAllCommand extends Command {
    {
        setDescription("Kill all mobs in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        MapleMap map = player.getMap();
        List<MapObject> monsters = map.getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapObjectType.MONSTER));
        int count = 0;
        for (MapObject monstermo : monsters) {
            Monster monster = (Monster) monstermo;
            if (!monster.getStats().isFriendly() && !(monster.getId() >= MobId.DEAD_HORNTAIL_MIN && monster.getId() <= MobId.HORNTAIL)) {
                map.damageMonster(player, monster, Integer.MAX_VALUE);
                count++;
            }
        }
        player.dropMessage(5, "Killed " + count + " monsters.");
    }
}
