/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.world.World;

public class MapPlayersCommand extends Command {
    {
        setDescription("Show all players on the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        String names = "";
        int map = player.getMapId();
        for (World world : Server.getInstance().getWorlds()) {
            for (Character chr : world.getPlayerStorage().getAllCharacters()) {
                int curMap = chr.getMapId();
                String hp = Integer.toString(chr.getHp());
                String maxhp = Integer.toString(chr.getCurrentMaxHp());
                String name = chr.getName() + ": " + hp + "/" + maxhp;
                if (map == curMap) {
                    names = names.equals("") ? name : (names + ", " + name);
                }
            }
        }
        player.message("Players on mapid " + map + ": " + names);
    }
}
