/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.Monster;

public class BossHpCommand extends Command {
    {
        setDescription("Show HP of bosses on current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Monster monster : player.getMap().getAllMonsters()) {
            if (monster != null && monster.isBoss() && monster.getHp() > 0) {
                long percent = monster.getHp() * 100L / monster.getMaxHp();
                String bar = "[";
                for (int i = 0; i < 100; i++) {
                    bar += i < percent ? "|" : ".";
                }
                bar += "]";
                player.yellowMessage(monster.getName() + " (" + monster.getId() + ") has " + percent + "% HP left.");
                player.yellowMessage("HP: " + bar);
            }
        }
    }
}
