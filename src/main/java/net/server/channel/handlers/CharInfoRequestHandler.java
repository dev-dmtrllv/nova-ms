package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.MapObject;
import tools.PacketCreator;

public final class CharInfoRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.skip(4);
        int cid = p.readInt();
        MapObject target = c.getPlayer().getMap().getMapObject(cid);
        if (target != null) {
            if (target instanceof Character player) {

                if (c.getPlayer().getId() != player.getId()) {
                    player.exportExcludedItems(c);
                }
                c.sendPacket(PacketCreator.charInfo(player));
            }
        }
    }
}
