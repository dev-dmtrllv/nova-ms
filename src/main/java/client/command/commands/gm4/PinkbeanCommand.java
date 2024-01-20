/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import server.life.LifeFactory;

public class PinkbeanCommand extends Command {
    {
        setDescription("Spawn Pink Bean on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(MobId.PINK_BEAN), player.getPosition());

    }
}
