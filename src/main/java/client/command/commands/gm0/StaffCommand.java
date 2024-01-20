/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;
import constants.id.NpcId;

public class StaffCommand extends Command {
    {
        setDescription("Show credits. These people made the server possible.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getAbstractPlayerInteraction().openNpc(NpcId.HERACLE, "credits");
    }
}
