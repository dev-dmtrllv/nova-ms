/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.Item;
import constants.id.ItemId;
import constants.id.MapId;

import java.awt.*;

public class SeedCommand extends Command {
    {
        setDescription("Drop all seeds inside Henesys PQ.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (player.getMapId() != MapId.HENESYS_PQ) {
            player.yellowMessage("This command can only be used in HPQ.");
            return;
        }
        Point[] pos = {new Point(7, -207), new Point(179, -447), new Point(-3, -687), new Point(-357, -687), new Point(-538, -447), new Point(-359, -207)};
        int[] seed = {ItemId.PINK_PRIMROSE_SEED, ItemId.PURPLE_PRIMROSE_SEED, ItemId.GREEN_PRIMROSE_SEED,
                ItemId.BLUE_PRIMROSE_SEED, ItemId.YELLOW_PRIMROSE_SEED, ItemId.BROWN_PRIMROSE_SEED};
        for (int i = 0; i < pos.length; i++) {
            Item item = new Item(seed[i], (byte) 0, (short) 1);
            player.getMap().spawnItemDrop(player, player, item, pos[i], false, true);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
