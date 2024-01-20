package net.server.handlers.login;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public final class RelogRequestHandler extends AbstractPacketHandler {
    @Override
    public boolean validateState(Client c) {
        return !c.isLoggedIn();
    }

    @Override
    public final void handlePacket(InPacket p, Client c) {
        c.sendPacket(PacketCreator.getRelogResponse());
    }
}
