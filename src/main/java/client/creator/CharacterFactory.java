package client.creator;

import client.Character;
import client.Client;
import client.SkinColor;
import client.inventory.Inventory;
import client.inventory.InventoryType;
import client.inventory.Item;
import config.YamlConfig;
import net.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.ItemInformationProvider;
import tools.PacketCreator;
/**
 * @author RonanLana
 */
public abstract class CharacterFactory {
    private static final Logger log = LoggerFactory.getLogger(CharacterFactory.class);

    protected synchronized static int createNewCharacter(Client c, String name, int face, int hair, int skin, int gender, CharacterFactoryRecipe recipe) {
        if (YamlConfig.config.server.COLLECTIVE_CHARSLOT ? c.getAvailableCharacterSlots() <= 0 : c.getAvailableCharacterWorldSlots() <= 0) {
            return -3;
        }

        if (!Character.canCreateChar(name)) {
            return -1;
        }

        Character newchar = Character.getDefault(c);
        newchar.setWorld(c.getWorld());
        newchar.setSkinColor(SkinColor.getById(skin));
        newchar.setGender(gender);
        newchar.setName(name);
        newchar.setHair(hair);
        newchar.setFace(face);

        newchar.setLevel(recipe.getLevel());
        newchar.setJob(recipe.getJob());
        newchar.setMapId(recipe.getMap());

        Inventory equipped = newchar.getInventory(InventoryType.EQUIPPED);
        ItemInformationProvider ii = ItemInformationProvider.getInstance();

        int top = recipe.getTop(), bottom = recipe.getBottom(), shoes = recipe.getShoes(), weapon = recipe.getWeapon();

        if (top > 0) {
            Item eq_top = ii.getEquipById(top);
            eq_top.setPosition((byte) -5);
            equipped.addItemFromDB(eq_top);
        }

        if (bottom > 0) {
            Item eq_bottom = ii.getEquipById(bottom);
            eq_bottom.setPosition((byte) -6);
            equipped.addItemFromDB(eq_bottom);
        }

        if (shoes > 0) {
            Item eq_shoes = ii.getEquipById(shoes);
            eq_shoes.setPosition((byte) -7);
            equipped.addItemFromDB(eq_shoes);
        }

        if (weapon > 0) {
            Item eq_weapon = ii.getEquipById(weapon);
            eq_weapon.setPosition((byte) -11);
            equipped.addItemFromDB(eq_weapon.copy());
        }

        if (!newchar.insertNewChar(recipe)) {
            return -2;
        }
        c.sendPacket(PacketCreator.addNewCharEntry(newchar));

        Server.getInstance().createCharacterEntry(newchar);
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.sendYellowTip("[New Char]: " + c.getAccountName() + " has created a new character with IGN " + name));
        log.info("Account {} created chr with name {}", c.getAccountName(), name);

        return 0;
    }
}
