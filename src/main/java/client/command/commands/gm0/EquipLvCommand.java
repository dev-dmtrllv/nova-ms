/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;

public class EquipLvCommand extends Command {
    {
        setDescription("Show levels of all equipped items.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getPlayer().showAllEquipFeatures();
    }
}
