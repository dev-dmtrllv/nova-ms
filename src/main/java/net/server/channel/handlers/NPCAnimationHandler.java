package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.opcodes.SendOpcode;
import net.packet.InPacket;
import net.packet.OutPacket;

public final class NPCAnimationHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (c.getPlayer().isChangingMaps()) {   // possible cause of error 38 in some map transition scenarios, thanks Arnah
            return;
        }

        OutPacket op = OutPacket.create(SendOpcode.NPC_ACTION);
        int length = p.available();
        if (length == 6) { // NPC Talk
            op.writeInt(p.readInt());
            op.writeByte(p.readByte());   // 2 bytes, thanks resinate
            op.writeByte(p.readByte());
        } else if (length > 6) { // NPC Move
            byte[] bytes = p.readBytes(length - 9);
            op.writeBytes(bytes);
        }
        c.sendPacket(op);
    }
}
