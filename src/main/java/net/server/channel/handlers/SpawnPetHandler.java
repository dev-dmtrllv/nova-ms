package net.server.channel.handlers;

import client.Client;
import client.processor.action.SpawnPetProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class SpawnPetHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt();
        byte slot = p.readByte();
        p.readByte();
        boolean lead = p.readByte() == 1;

        SpawnPetProcessor.processSpawnPet(c, slot, lead);
    }
}
