/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Client;
import client.command.Command;
import server.life.PlayerNPC;

public class EraseAllPNpcsCommand extends Command {
    {
        setDescription("Remove all player NPCs.");
    }

    @Override
    public void execute(Client c, String[] params) {
        PlayerNPC.removeAllPlayerNPC();
    }
}
