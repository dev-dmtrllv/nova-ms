package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import scripting.reactor.ReactorScriptManager;
import server.maps.Reactor;
/**
 * @author Generic
 */
public final class TouchReactorHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        int oid = p.readInt();
        Reactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (reactor == null) {
            return;
        }

        if (p.readByte() != 0) {
            ReactorScriptManager.getInstance().touch(c, reactor);
        } else {
            ReactorScriptManager.getInstance().untouch(c, reactor);
        }
    }
}
