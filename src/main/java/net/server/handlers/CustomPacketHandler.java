package net.server.handlers;

import client.Client;
import net.PacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public class CustomPacketHandler implements PacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        if (p.available() > 0 && c.getGMLevel() == 4) {//w/e
            c.sendPacket(PacketCreator.customPacket(p.readBytes(p.available())));
        }
    }

    @Override
    public boolean validateState(Client c) {
        return true;
    }
}
