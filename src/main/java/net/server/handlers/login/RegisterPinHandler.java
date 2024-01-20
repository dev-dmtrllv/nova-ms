package net.server.handlers.login;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.coordinator.session.SessionCoordinator;
import tools.PacketCreator;
/*
 * @author Rob
 */
public final class RegisterPinHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        byte c2 = p.readByte();
        if (c2 == 0) {
            SessionCoordinator.getInstance().closeSession(c, null);
            c.updateLoginState(Client.LOGIN_NOTLOGGEDIN);
        } else {
            String pin = p.readString();
            if (pin != null) {
                c.setPin(pin);
                c.sendPacket(PacketCreator.pinRegistered());

                SessionCoordinator.getInstance().closeSession(c, null);
                c.updateLoginState(Client.LOGIN_NOTLOGGEDIN);
            }
        }
    }
}
