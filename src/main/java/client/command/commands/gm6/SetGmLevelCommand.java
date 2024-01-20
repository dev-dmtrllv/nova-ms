/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;

public class SetGmLevelCommand extends Command {
    {
        setDescription("Set GM level of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !setgmlevel <playername> <newlevel>");
            return;
        }

        int newLevel = Integer.parseInt(params[1]);
        Character target = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);
        if (target != null) {
            target.setGMLevel(newLevel);
            target.getClient().setGMLevel(newLevel);

            target.dropMessage("You are now a level " + newLevel + " GM. See @commands for a list of available commands.");
            player.dropMessage(target + " is now a level " + newLevel + " GM.");
        } else {
            player.dropMessage("Player '" + params[0] + "' was not found on this channel.");
        }
    }
}
