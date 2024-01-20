package client.creator.veteran;

import client.Client;
import client.Job;
import client.Skill;
import client.SkillFactory;
import client.creator.CharacterFactory;
import client.creator.CharacterFactoryRecipe;
import client.inventory.InventoryType;
import client.inventory.Item;
import constants.id.ItemId;
import constants.id.MapId;
import constants.skills.Magician;
import server.ItemInformationProvider;
/**
 * @author RonanLana
 */
public class MagicianCreator extends CharacterFactory {
    private static final int[] equips = {0, ItemId.PURPLE_FAIRY_TOP, 0, ItemId.PURPLE_FAIRY_SKIRT, ItemId.RED_MAGICSHOES};
    private static final int[] weapons = {ItemId.MITHRIL_WAND, ItemId.CIRCLE_WINDED_STAFF};
    private static final int[] startingHpMp = {405, 729};
    private static final int[] mpGain = {0, 40, 80, 118, 156, 194, 230, 266, 302, 336, 370};

    private static CharacterFactoryRecipe createRecipe(Job job, int level, int map, int top, int bottom, int shoes, int weapon, int gender, int improveSp) {
        CharacterFactoryRecipe recipe = new CharacterFactoryRecipe(job, level, map, top, bottom, shoes, weapon);
        ItemInformationProvider ii = ItemInformationProvider.getInstance();

        recipe.setInt(20);
        recipe.setRemainingAp(138);
        recipe.setRemainingSp(67);

        recipe.setMaxHp(startingHpMp[0]);
        recipe.setMaxMp(startingHpMp[1] + mpGain[improveSp]);

        recipe.setMeso(100000);

        if (gender == 0) {
            giveEquipment(recipe, ii, ItemId.BLUE_WIZARD_ROBE);
        }

        for (int i = 1; i < weapons.length; i++) {
            giveEquipment(recipe, ii, weapons[i]);
        }

        giveItem(recipe, ItemId.ORANGE_POTION, 100, InventoryType.USE);
        giveItem(recipe, ItemId.MANA_ELIXIR, 100, InventoryType.USE);
        giveItem(recipe, ItemId.RELAXER, 1, InventoryType.SETUP);

        if (improveSp > 0) {
            improveSp += 5;
            recipe.setRemainingSp(recipe.getRemainingSp() - improveSp);

            int toUseSp = 5;
            Skill improveMpRec = SkillFactory.getSkill(Magician.IMPROVED_MP_RECOVERY);
            recipe.addStartingSkillLevel(improveMpRec, toUseSp);
            improveSp -= toUseSp;

            if (improveSp > 0) {
                Skill improveMaxMp = SkillFactory.getSkill(Magician.IMPROVED_MAX_MP_INCREASE);
                recipe.addStartingSkillLevel(improveMaxMp, improveSp);
            }
        }

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
        return createNewCharacter(c, name, face, hair, skin, gender, createRecipe(Job.MAGICIAN, 30, MapId.ELLINIA, equips[gender], equips[2 + gender], equips[4], weapons[0], gender, improveSp));
    }
}
