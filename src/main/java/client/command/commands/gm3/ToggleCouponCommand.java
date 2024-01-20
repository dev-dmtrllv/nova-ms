/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;

public class ToggleCouponCommand extends Command {
    {
        setDescription("Toggle enable/disable a coupon.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !togglecoupon <itemid>");
            return;
        }
        Server.getInstance().toggleCoupon(Integer.parseInt(params[0]));
    }
}
