/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import server.life.LifeFactory;

public class ZakumCommand extends Command {
    {
        setDescription("Spawn Zakum on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.getMap().spawnFakeMonsterOnGroundBelow(LifeFactory.getMonster(MobId.ZAKUM_1), player.getPosition());
        for (int mobId = MobId.ZAKUM_ARM_1; mobId <= MobId.ZAKUM_ARM_8; mobId++) {
            player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(mobId), player.getPosition());
        }
    }
}
