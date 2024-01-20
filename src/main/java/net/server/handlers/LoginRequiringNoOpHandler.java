package net.server.handlers;

import client.Client;
import net.PacketHandler;
import net.packet.InPacket;

public final class LoginRequiringNoOpHandler implements PacketHandler {
    private static final LoginRequiringNoOpHandler instance = new LoginRequiringNoOpHandler();

    public static LoginRequiringNoOpHandler getInstance() {
        return instance;
    }

    public void handlePacket(InPacket p, Client c) {
    }

    public boolean validateState(Client c) {
        return c.isLoggedIn();
    }
}
