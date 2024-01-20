/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.Monster;

public class MobHpCommand extends Command {
    {
        setDescription("Show HP of mobs on current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Monster monster : player.getMap().getAllMonsters()) {
            if (monster != null && monster.getHp() > 0) {
                player.yellowMessage(monster.getName() + " (" + monster.getId() + ") has " + monster.getHp() + " / " + monster.getMaxHp() + " HP.");

            }
        }
    }
}
