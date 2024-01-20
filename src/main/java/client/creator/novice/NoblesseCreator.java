package client.creator.novice;

import client.Client;
import client.Job;
import client.creator.CharacterFactory;
import client.creator.CharacterFactoryRecipe;
import client.inventory.InventoryType;
import constants.id.ItemId;
import constants.id.MapId;
/**
 * @author RonanLana
 */
public class NoblesseCreator extends CharacterFactory {

    private static CharacterFactoryRecipe createRecipe(Job job, int level, int map, int top, int bottom, int shoes, int weapon) {
        CharacterFactoryRecipe recipe = new CharacterFactoryRecipe(job, level, map, top, bottom, shoes, weapon);
        giveItem(recipe, ItemId.NOBLESSE_GUIDE, 1, InventoryType.ETC);
        return recipe;
    }

    private static void giveItem(CharacterFactoryRecipe recipe, int itemid, int quantity, InventoryType itemType) {
        recipe.addStartingItem(itemid, quantity, itemType);
    }

    public static int createCharacter(Client c, String name, int face, int hair, int skin, int top, int bottom, int shoes, int weapon, int gender) {
        int status = createNewCharacter(c, name, face, hair, skin, gender, createRecipe(Job.NOBLESSE, 1, MapId.STARTING_MAP_NOBLESSE, top, bottom, shoes, weapon));
        return status;
    }
}
