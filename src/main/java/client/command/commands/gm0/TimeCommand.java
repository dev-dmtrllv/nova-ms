/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeCommand extends Command {
    {
        setDescription("Show current server time.");
    }

    @Override
    public void execute(Client client, String[] params) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getDefault());
        client.getPlayer().yellowMessage("NovaMS Server Time: " + dateFormat.format(new Date()));
    }
}
