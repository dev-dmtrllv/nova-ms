/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Character;
import client.Client;
import client.command.Command;
import config.YamlConfig;

public class StatLukCommand extends Command {
    {
        setDescription("Assign AP into LUK.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int remainingAp = player.getRemainingAp();

        int amount;
        if (params.length > 0) {
            try {
                amount = Math.min(Integer.parseInt(params[0]), remainingAp);
            } catch (NumberFormatException e) {
                player.dropMessage("That is not a valid number!");
                return;
            }
        } else {
            amount = Math.min(remainingAp, YamlConfig.config.server.MAX_AP - player.getLuk());
        }
        if (!player.assignLuk(Math.max(amount, 0))) {
            player.dropMessage("Please make sure your AP is not over " + YamlConfig.config.server.MAX_AP + " and you have enough to distribute.");
        }
    }
}
