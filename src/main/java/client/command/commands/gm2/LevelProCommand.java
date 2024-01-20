/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class LevelProCommand extends Command {
    {
        setDescription("Set your level, one by one.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !levelpro <newlevel>");
            return;
        }
        while (player.getLevel() < Math.min(player.getMaxClassLevel(), Integer.parseInt(params[0]))) {
            player.levelUp(false);
        }
    }
}
