/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.autoban.AutobanFactory;
import client.command.Command;

public class IgnoredCommand extends Command {
    {
        setDescription("Show all characters being ignored in auto-ban alerts.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (int chrId : AutobanFactory.getIgnoredChrIds()) {
            player.yellowMessage(Character.getNameById(chrId) + " is being ignored.");
        }
    }
}
