/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Client;
import client.command.Command;
import server.ShopFactory;

public class GmShopCommand extends Command {
    {
        setDescription("Open the GM shop.");
    }

    @Override
    public void execute(Client c, String[] params) {
        ShopFactory.getInstance().getShop(1337).sendShop(c);
    }
}
