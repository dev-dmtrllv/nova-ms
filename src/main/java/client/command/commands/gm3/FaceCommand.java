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

public class FaceCommand extends Command {
    {
        setDescription("Change face of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !face [<playername>] <faceid>");
            return;
        }

        try {
            if (params.length == 1) {
                int itemId = Integer.parseInt(params[0]);
                if (!ItemConstants.isFace(itemId) || ItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Face id '" + params[0] + "' does not exist.");
                    return;
                }

                player.setFace(itemId);
                player.updateSingleStat(Stat.FACE, itemId);
                player.equipChanged();
            } else {
                int itemId = Integer.parseInt(params[1]);
                if (!ItemConstants.isFace(itemId) || ItemInformationProvider.getInstance().getName(itemId) == null) {
                    player.yellowMessage("Face id '" + params[1] + "' does not exist.");
                }

                Character victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);
                if (victim != null) {
                    victim.setFace(itemId);
                    victim.updateSingleStat(Stat.FACE, itemId);
                    victim.equipChanged();
                } else {
                    player.yellowMessage("Player '" + params[0] + "' has not been found on this channel.");
                }
            }
        } catch (Exception e) {
        }

    }
}
