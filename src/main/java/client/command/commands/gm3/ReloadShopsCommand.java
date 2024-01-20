/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Client;
import client.command.Command;
import server.ShopFactory;
public class ReloadShopsCommand extends Command {
    {
        setDescription("Reload popup shops and NPC shops.");
    }

    @Override
    public void execute(Client c, String[] params) {
        ShopFactory.getInstance().reloadShops();
    }
}
