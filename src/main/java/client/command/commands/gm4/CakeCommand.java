/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import server.life.LifeFactory;
import server.life.Monster;

public class CakeCommand extends Command {
    {
        setDescription("Spawn Cake boss with specified HP.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Monster monster = LifeFactory.getMonster(MobId.GIANT_CAKE);
        if (params.length == 1) {
            double mobHp = Double.parseDouble(params[0]);
            int newHp = (mobHp <= 0) ? Integer.MAX_VALUE : ((mobHp > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) mobHp);

            monster.setStartingHp(newHp);
        }

        player.getMap().spawnMonsterOnGroundBelow(monster, player.getPosition());
    }
}
