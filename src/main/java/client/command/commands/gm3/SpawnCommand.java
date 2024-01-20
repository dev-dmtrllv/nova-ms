/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.LifeFactory;
import server.life.Monster;

public class SpawnCommand extends Command {
    {
        setDescription("Spawn mob(s) on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !spawn <mobid> [<mobqty>]");
            return;
        }

        Monster monster = LifeFactory.getMonster(Integer.parseInt(params[0]));
        if (monster == null) {
            return;
        }
        if (params.length == 2) {
            for (int i = 0; i < Integer.parseInt(params[1]); i++) {
                player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(Integer.parseInt(params[0])), player.getPosition());
            }
        } else {
            player.getMap().spawnMonsterOnGroundBelow(LifeFactory.getMonster(Integer.parseInt(params[0])), player.getPosition());
        }
    }
}
