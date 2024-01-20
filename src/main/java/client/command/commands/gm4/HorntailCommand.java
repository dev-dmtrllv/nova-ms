/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapleMap;

import java.awt.*;

public class HorntailCommand extends Command {
    {
        setDescription("Spawn Horntail on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        final Point targetPoint = player.getPosition();
        final MapleMap targetMap = player.getMap();

        targetMap.spawnHorntailOnGroundBelow(targetPoint);
    }
}
