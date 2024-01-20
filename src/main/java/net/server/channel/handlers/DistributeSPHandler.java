package net.server.channel.handlers;

import client.Client;
import client.processor.stat.AssignSPProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class DistributeSPHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt();
        int skillid = p.readInt();

        AssignSPProcessor.SPAssignAction(c, skillid);
    }
}
