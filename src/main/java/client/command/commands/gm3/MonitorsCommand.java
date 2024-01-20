/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.packet.logging.MonitoredChrLogger;

public class MonitorsCommand extends Command {
    {
        setDescription("Show all characters being monitored for packet logging");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (int chrId : MonitoredChrLogger.getMonitoredChrIds()) {
            player.yellowMessage(Character.getNameById(chrId) + " is being monitored.");
        }
    }
}
