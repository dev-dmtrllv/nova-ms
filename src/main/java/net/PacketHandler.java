package net;

import client.Client;
import net.packet.InPacket;

public interface PacketHandler {
    void handlePacket(InPacket p, Client c);
    boolean validateState(Client c);
}
