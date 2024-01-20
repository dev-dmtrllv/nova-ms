package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public final class MonsterBookCoverHandler extends AbstractPacketHandler {
    public final void handlePacket(InPacket p, Client c) {
        int id = p.readInt();
        if (id == 0 || id / 10000 == 238) {
            c.getPlayer().setMonsterBookCover(id);
            c.sendPacket(PacketCreator.changeCover(id));
        }
    }
}
