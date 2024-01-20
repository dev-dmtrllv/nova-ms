/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.MobId;
import server.life.LifeFactory;

public class PapCommand extends Command {
    {
        setDescription("Spawn Papulatus on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();

        // thanks Conrad for noticing mobid typo here
        player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(MobId.PAPULATUS_CLOCK), player.getPosition());
    }
}
