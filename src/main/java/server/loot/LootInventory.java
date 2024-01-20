package server.loot;

import client.Character;
import client.inventory.InventoryType;
import client.inventory.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ronan
 */
public class LootInventory {
    Map<Integer, Integer> items = new HashMap<>(50);

    public LootInventory(Character from) {
        for (InventoryType values : InventoryType.values()) {

            for (Item it : from.getInventory(values).list()) {
                Integer itemQty = items.get(it.getItemId());

                if (itemQty == null) {
                    items.put(it.getItemId(), (int) it.getQuantity());
                } else {
                    items.put(it.getItemId(), itemQty + it.getQuantity());
                }
            }
        }
    }

    public int hasItem(int itemid, int quantity) {
        Integer itemQty = items.get(itemid);
        return itemQty == null ? 0 : itemQty >= quantity ? 2 : itemQty > 0 ? 1 : 0;
    }

}
