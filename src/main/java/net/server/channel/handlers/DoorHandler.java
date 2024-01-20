package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.DoorObject;
import server.maps.MapObject;
import tools.PacketCreator;
/**
 * @author Matze
 */
public final class DoorHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int ownerid = p.readInt();
        p.readByte(); // specifies if backwarp or not, 1 town to target, 0 target to town

        Character chr = c.getPlayer();
        if (chr.isChangingMaps() || chr.isBanned()) {
            c.sendPacket(PacketCreator.enableActions());
            return;
        }

        for (MapObject obj : chr.getMap().getMapObjects()) {
            if (obj instanceof DoorObject door) {
                if (door.getOwnerId() == ownerid) {
                    door.warp(chr);
                    return;
                }
            }
        }

        c.sendPacket(PacketCreator.blockedMessage(6));
        c.sendPacket(PacketCreator.enableActions());
    }
}
