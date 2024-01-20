/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.Equip;
import client.inventory.InventoryType;
import client.inventory.Item;
import client.inventory.manipulator.InventoryManipulator;
import constants.inventory.ItemConstants;
import server.ItemInformationProvider;

public class ProItemCommand extends Command {
    {
        setDescription("Spawn an item with custom stats.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !proitem <itemid> <stat value> [<spdjmp value>]");
            return;
        }

        ItemInformationProvider ii = ItemInformationProvider.getInstance();
        int itemid = Integer.parseInt(params[0]);

        if (ii.getName(itemid) == null) {
            player.yellowMessage("Item id '" + params[0] + "' does not exist.");
            return;
        }

        short stat = (short) Math.max(0, Short.parseShort(params[1]));
        short spdjmp = params.length >= 3 ? (short) Math.max(0, Short.parseShort(params[2])) : 0;

        InventoryType type = ItemConstants.getInventoryType(itemid);
        if (type.equals(InventoryType.EQUIP)) {
            Item it = ii.getEquipById(itemid);
            it.setOwner(player.getName());

            hardsetItemStats((Equip) it, stat, spdjmp);
            InventoryManipulator.addFromDrop(c, it);
        } else {
            player.dropMessage(6, "Make sure it's an equippable item.");
        }
    }

    private static void hardsetItemStats(Equip equip, short stat, short spdjmp) {
        equip.setStr(stat);
        equip.setDex(stat);
        equip.setInt(stat);
        equip.setLuk(stat);
        equip.setMatk(stat);
        equip.setWatk(stat);
        equip.setAcc(stat);
        equip.setAvoid(stat);
        equip.setJump(spdjmp);
        equip.setSpeed(spdjmp);
        equip.setWdef(stat);
        equip.setMdef(stat);
        equip.setHp(stat);
        equip.setMp(stat);

        short flag = equip.getFlag();
        flag |= ItemConstants.UNTRADEABLE;
        equip.setFlag(flag);
    }
}
