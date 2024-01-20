/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.Equip;
import client.inventory.Inventory;
import client.inventory.InventoryType;
import constants.inventory.ItemConstants;

public class SetEqStatCommand extends Command {
    {
        setDescription("Set stats of all equips in inventory.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !seteqstat <stat value> [<spdjmp value>]");
            return;
        }

        short newStat = (short) Math.max(0, Integer.parseInt(params[0]));
        short newSpdJmp = params.length >= 2 ? (short) Integer.parseInt(params[1]) : 0;
        Inventory equip = player.getInventory(InventoryType.EQUIP);

        for (byte i = 1; i <= equip.getSlotLimit(); i++) {
            try {
                Equip eq = (Equip) equip.getItem(i);
                if (eq == null) {
                    continue;
                }

                eq.setWdef(newStat);
                eq.setAcc(newStat);
                eq.setAvoid(newStat);
                eq.setJump(newSpdJmp);
                eq.setMatk(newStat);
                eq.setMdef(newStat);
                eq.setHp(newStat);
                eq.setMp(newStat);
                eq.setSpeed(newSpdJmp);
                eq.setWatk(newStat);
                eq.setDex(newStat);
                eq.setInt(newStat);
                eq.setStr(newStat);
                eq.setLuk(newStat);

                short flag = eq.getFlag();
                flag |= ItemConstants.UNTRADEABLE;
                eq.setFlag(flag);

                player.forceUpdateItem(eq);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
