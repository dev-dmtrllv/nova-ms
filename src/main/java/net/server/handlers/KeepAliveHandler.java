package net.server.handlers;

import client.Client;
import net.PacketHandler;
import net.packet.InPacket;

public class KeepAliveHandler implements PacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        c.pongReceived();
    }

    @Override
    public boolean validateState(Client c) {
        return true;
    }
}
