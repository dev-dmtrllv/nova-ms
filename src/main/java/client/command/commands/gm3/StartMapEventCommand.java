/*
   @Author: MedicOP - Start actual event
*/
package client.command.commands.gm3;

import client.Client;
import client.command.Command;

public class StartMapEventCommand extends Command {
    {
        setDescription("Start a \"classic\" event on current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getPlayer().getMap().startEvent(c.getPlayer());
    }
}
