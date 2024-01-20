package net;

import client.Client;
import net.server.Server;

public abstract class AbstractPacketHandler implements PacketHandler {
    @Override
    public boolean validateState(Client c) {
        return c.isLoggedIn();
    }

    protected static long currentServerTime() {
        return Server.getInstance().getCurrentTime();
    }
}
