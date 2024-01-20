/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import scripting.portal.PortalScriptManager;

public class ReloadPortalsCommand extends Command {
    {
        setDescription("Reload all portal scripts.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        PortalScriptManager.getInstance().reloadPortalScripts();
        player.dropMessage(5, "Reloaded Portals");
    }
}
