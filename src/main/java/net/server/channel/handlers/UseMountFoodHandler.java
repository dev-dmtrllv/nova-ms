package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.Mount;
import client.inventory.Inventory;
import client.inventory.InventoryType;
import client.inventory.Item;
import client.inventory.manipulator.InventoryManipulator;
import constants.game.ExpTable;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author PurpleMadness
 * @author Ronan
 */
public final class UseMountFoodHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.skip(4);
        short pos = p.readShort();
        int itemid = p.readInt();

        Character chr = c.getPlayer();
        Mount mount = chr.getMount();
        Inventory useInv = chr.getInventory(InventoryType.USE);

        if (c.tryacquireClient()) {
            try {
                Boolean mountLevelup = null;

                useInv.lockInventory();
                try {
                    Item item = useInv.getItem(pos);
                    if (item != null && item.getItemId() == itemid && mount != null) {
                        int curTiredness = mount.getTiredness();
                        int healedTiredness = Math.min(curTiredness, 30);

                        float healedFactor = (float) healedTiredness / 30;
                        mount.setTiredness(curTiredness - healedTiredness);

                        if (healedFactor > 0.0f) {
                            mount.setExp(mount.getExp() + (int) Math.ceil(healedFactor * (2 * mount.getLevel() + 6)));
                            int level = mount.getLevel();
                            boolean levelup = mount.getExp() >= ExpTable.getMountExpNeededForLevel(level) && level < 31;
                            if (levelup) {
                                mount.setLevel(level + 1);
                            }

                            mountLevelup = levelup;
                        }

                        InventoryManipulator.removeById(c, InventoryType.USE, itemid, 1, true, false);
                    }
                } finally {
                    useInv.unlockInventory();
                }

                if (mountLevelup != null) {
                    chr.getMap().broadcastMessage(PacketCreator.updateMount(chr.getId(), mount, mountLevelup));
                }
            } finally {
                c.releaseClient();
            }
        }
    }
}
