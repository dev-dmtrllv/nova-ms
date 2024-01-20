package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public final class UseDeathItemHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int itemId = p.readInt();
        c.getPlayer().setItemEffect(itemId);
        c.sendPacket(PacketCreator.itemEffect(c.getPlayer().getId(), itemId));
    }
}
