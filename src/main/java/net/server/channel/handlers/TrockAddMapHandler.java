package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.FieldLimit;
import tools.PacketCreator;
/**
 * @author kevintjuh93
 */
public final class TrockAddMapHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        byte type = p.readByte();
        boolean vip = p.readByte() == 1;
        if (type == 0x00) {
            int mapId = p.readInt();
            if (vip) {
                chr.deleteFromVipTrocks(mapId);
            } else {
                chr.deleteFromTrocks(mapId);
            }
            c.sendPacket(PacketCreator.trockRefreshMapList(chr, true, vip));
        } else if (type == 0x01) {
            if (!FieldLimit.CANNOTVIPROCK.check(chr.getMap().getFieldLimit())) {
                if (vip) {
                    chr.addVipTrockMap();
                } else {
                    chr.addTrockMap();
                }

                c.sendPacket(PacketCreator.trockRefreshMapList(chr, false, vip));
            } else {
                chr.message("You may not save this map.");
            }
        }
    }
}
