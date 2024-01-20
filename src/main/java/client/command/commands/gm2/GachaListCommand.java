package client.command.commands.gm2;

import client.Client;
import client.command.Command;
/**
 * @author Ronan
 */
public class GachaListCommand extends Command {
    {
        setDescription("Show gachapon rewards.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getAbstractPlayerInteraction().openNpc(10000, "gachaponInfo");
    }
}
