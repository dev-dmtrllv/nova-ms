package net.server.channel.handlers;

import client.Client;
import client.processor.stat.AssignAPProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class DistributeAPHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt();
        int num = p.readInt();

        AssignAPProcessor.APAssignAction(c, num);
    }
}
