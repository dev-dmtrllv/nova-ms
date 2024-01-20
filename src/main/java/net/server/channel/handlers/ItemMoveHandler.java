package net.server.channel.handlers;

import client.Client;
import client.inventory.InventoryType;
import client.inventory.manipulator.InventoryManipulator;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Matze
 */
public final class ItemMoveHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.skip(4);
        if (c.getPlayer().getAutobanManager().getLastSpam(6) + 300 > currentServerTime()) {
            c.sendPacket(PacketCreator.enableActions());
            return;
        }

        InventoryType type = InventoryType.getByType(p.readByte());
        short src = p.readShort();     //is there any reason to use byte instead of short in src and action?
        short action = p.readShort();
        short quantity = p.readShort();

        if (src < 0 && action > 0) {
            InventoryManipulator.unequip(c, src, action);
        } else if (action < 0) {
            InventoryManipulator.equip(c, src, action);
        } else if (action == 0) {
            InventoryManipulator.drop(c, type, src, quantity);
        } else {
            InventoryManipulator.move(c, type, src, action);
        }

        c.getPlayer().getAutobanManager().spam(6);
    }
}
