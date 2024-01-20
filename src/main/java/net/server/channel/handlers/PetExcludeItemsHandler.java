package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.autoban.AutobanFactory;
import client.inventory.Pet;
import net.AbstractPacketHandler;
import net.packet.InPacket;
/**
 * @author BubblesDev
 * @author Ronan
 */
public final class PetExcludeItemsHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        final int petId = p.readInt();
        p.skip(4); // timestamp

        Character chr = c.getPlayer();
        byte petIndex = chr.getPetIndex(petId);
        if (petIndex < 0) {
            return;
        }

        final Pet pet = chr.getPet(petIndex);
        if (pet == null) {
            return;
        }

        chr.resetExcluded(petId);
        byte amount = p.readByte();
        for (int i = 0; i < amount; i++) {
            int itemId = p.readInt();
            if (itemId >= 0) {
                chr.addExcluded(petId, itemId);
            } else {
                AutobanFactory.PACKET_EDIT.alert(chr, "negative item id value in PetExcludeItemsHandler (" + itemId + ")");
                return;
            }
        }
        chr.commitExcludedItems();
    }
}
