package client.creator.veteran;

import client.Client;
import client.Job;
import client.creator.CharacterFactory;
import client.creator.CharacterFactoryRecipe;
import client.inventory.InventoryType;
import client.inventory.Item;
import constants.id.ItemId;
import constants.id.MapId;
import server.ItemInformationProvider;
/**
 * @author RonanLana
 */
public class BowmanCreator extends CharacterFactory {
    private static final int[] equips = {ItemId.GREEN_HUNTERS_ARMOR, ItemId.GREEN_HUNTRESS_ARMOR,
            ItemId.GREEN_HUNTERS_PANTS, ItemId.GREEN_HUNTRESS_PANTS, ItemId.GREEN_HUNTER_BOOTS};
    private static final int[] weapons = {ItemId.RYDEN, ItemId.MOUNTAIN_CROSSBOW};
    private static final int[] startingHpMp = {797, 404};

    private static CharacterFactoryRecipe createRecipe(Job job, int level, int map, int top, int bottom, int shoes, int weapon) {
        CharacterFactoryRecipe recipe = new CharacterFactoryRecipe(job, level, map, top, bottom, shoes, weapon);
        ItemInformationProvider ii = ItemInformationProvider.getInstance();

        recipe.setDex(25);
        recipe.setRemainingAp(133);
        recipe.setRemainingSp(61);

        recipe.setMaxHp(startingHpMp[0]);
        recipe.setMaxMp(startingHpMp[1]);

        recipe.setMeso(100000);

        for (int i = 1; i < weapons.length; i++) {
            giveEquipment(recipe, ii, weapons[i]);
        }

        giveItem(recipe, ItemId.WHITE_POTION, 100, InventoryType.USE);
        giveItem(recipe, ItemId.BLUE_POTION, 100, InventoryType.USE);
        giveItem(recipe, ItemId.RELAXER, 1, InventoryType.SETUP);

        return recipe;
    }

    private static void giveEquipment(CharacterFactoryRecipe recipe, ItemInformationProvider ii, int equipid) {
        Item nEquip = ii.getEquipById(equipid);
        recipe.addStartingEquipment(nEquip);
    }

    private static void giveItem(CharacterFactoryRecipe recipe, int itemid, int quantity, InventoryType itemType) {
        recipe.addStartingItem(itemid, quantity, itemType);
    }

    public static int createCharacter(Client c, String name, int face, int hair, int skin, int gender, int improveSp) {
        return createNewCharacter(c, name, face, hair, skin, gender, createRecipe(Job.BOWMAN, 30, MapId.HENESYS, equips[gender], equips[2 + gender], equips[4], weapons[0]));
    }
}
