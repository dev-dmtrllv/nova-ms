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
public class ThiefCreator extends CharacterFactory {
    private static final int[] equips = {ItemId.DARK_BROWN_STEALER, ItemId.RED_STEAL, ItemId.DARK_BROWN_STEALER_PANTS,
            ItemId.RED_STEAL_PANTS, ItemId.BRONZE_CHAIN_BOOTS};
    private static final int[] weapons = {ItemId.STEEL_GUARDS, ItemId.REEF_CLAW};
    private static final int[] startingHpMp = {794, 407};

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

        giveItem(recipe, ItemId.SUBI_THROWING_STARS, 500, InventoryType.USE);

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
        return createNewCharacter(c, name, face, hair, skin, gender, createRecipe(Job.THIEF, 30, MapId.KERNING_CITY, equips[gender], equips[2 + gender], equips[4], weapons[0]));
    }
}
