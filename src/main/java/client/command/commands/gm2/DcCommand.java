/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class DcCommand extends Command {
    {
        setDescription("Disconnect a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !dc <playername>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim == null) {
            victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);
            if (victim == null) {
                victim = player.getMap().getCharacterByName(params[0]);
                if (victim != null) {
                    try {//sometimes bugged because the map = null
                        victim.getClient().disconnect(true, false);
                        player.getMap().removePlayer(victim);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        }
        if (player.gmLevel() < victim.gmLevel()) {
            victim = player;
        }
        victim.getClient().disconnect(false, false);
    }
}
