/*
   @Author: MedicOP - Stop actual event
*/
package client.command.commands.gm3;

import client.Client;
import client.command.Command;

public class StopMapEventCommand extends Command {
    {
        setDescription("Stop ongoing \"classic\" event.");
    }

    @Override
    public void execute(Client c, String[] params) {
        c.getPlayer().getMap().setEventStarted(false);
    }
}
