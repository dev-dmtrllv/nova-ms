/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import net.server.Server;
import server.life.LifeFactory;
import tools.PacketCreator;

public class BombCommand extends Command {
    {
        setDescription("Bomb a player, dealing damage.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length > 0) {
            Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            if (victim != null) {
                victim.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(MobId.ARPQ_BOMB), victim.getPosition());
                Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(5, player.getName() + " used !bomb on " + victim.getName()));
            } else {
                player.message("Player '" + params[0] + "' could not be found on this world.");
            }
        } else {
            player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(MobId.ARPQ_BOMB), player.getPosition());
        }
    }
}
