/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.Stat;
import client.command.Command;
import constants.inventory.ItemConstants;
import server.ItemInformationProvider;

public class HairCommand extends Command {
    {
        setDescription("Change hair of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !hair [<playername>] <hairid>");
            return;
        }

        try {
            if (params.length == 1) {
                int itemId = Integer.parseInt(params[0]);
                if (!ItemConstants.isHair(itemId) || ItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Hair id '" + params[0] + "' does not exist.");
                    return;
                }

                player.setHair(itemId);
                player.updateSingleStat(Stat.HAIR, itemId);
                player.equipChanged();
            } else {
                int itemId = Integer.parseInt(params[1]);
                if (!ItemConstants.isHair(itemId) || ItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Hair id '" + params[1] + "' does not exist.");
                    return;
                }

                Character victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);
                if (victim != null) {
                    victim.setHair(itemId);
                    victim.updateSingleStat(Stat.HAIR, itemId);
                    victim.equipChanged();
                } else {
                    player.yellowMessage("Player '" + params[0] + "' has not been found on this channel.");
                }
            }
        } catch (Exception e) {
        }
    }
}
