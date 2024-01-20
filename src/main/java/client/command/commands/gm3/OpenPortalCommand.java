/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class OpenPortalCommand extends Command {
    {
        setDescription("Open a portal on the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !openportal <portalid>");
            return;
        }
        player.getMap().getPortal(params[0]).setPortalState(true);
    }
}
