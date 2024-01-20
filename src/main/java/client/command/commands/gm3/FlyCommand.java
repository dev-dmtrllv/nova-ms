/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;

public class FlyCommand extends Command {
    {
        setDescription("Enable/disable fly feature.");
    }

    @Override
    public void execute(Client c, String[] params) { // fly option will become available for any character of that account
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !fly <on/off>");
            return;
        }

        Integer accid = c.getAccID();
        Server srv = Server.getInstance();
        String sendStr = "";
        if (params[0].equalsIgnoreCase("on")) {
            sendStr += "Enabled Fly feature (F1). With fly active, you cannot attack.";
            if (!srv.canFly(accid)) {
                sendStr += " Re-login to take effect.";
            }

            srv.changeFly(c.getAccID(), true);
        } else {
            sendStr += "Disabled Fly feature. You can now attack.";
            if (srv.canFly(accid)) {
                sendStr += " Re-login to take effect.";
            }

            srv.changeFly(c.getAccID(), false);
        }

        player.dropMessage(6, sendStr);
    }
}
