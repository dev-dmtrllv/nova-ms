/*
   @Author: Ronan
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class SetSlotCommand extends Command {
    {
        setDescription("Set amount of inventory slots in all tabs.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !setslot <newlevel>");
            return;
        }

        int slots = (Integer.parseInt(params[0]) / 4) * 4;
        for (int i = 1; i < 5; i++) {
            int curSlots = player.getSlots(i);
            if (slots <= -curSlots) {
                continue;
            }

            player.gainSlots(i, slots - curSlots, true);
        }

        player.yellowMessage("Slots updated.");
    }
}
