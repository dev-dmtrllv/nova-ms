
package net.server.handlers.login;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.Server;
import net.server.coordinator.session.SessionCoordinator;
import tools.PacketCreator;
/**
 * @author kevintjuh93
 */
public class SetGenderHandler extends AbstractPacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        if (c.getGender() == 10) { //Packet shouldn't come if Gender isn't 10.
            byte confirmed = p.readByte();
            if (confirmed == 0x01) {
                c.setGender(p.readByte());
                c.sendPacket(PacketCreator.getAuthSuccess(c));

                Server.getInstance().registerLoginState(c);
            } else {
                SessionCoordinator.getInstance().closeSession(c, null);
                c.updateLoginState(Client.LOGIN_NOTLOGGEDIN);
            }
        }
    }

}
