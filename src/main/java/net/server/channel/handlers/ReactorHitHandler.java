package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.Reactor;
/**
 * @author Lerk
 */
public final class ReactorHitHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        //System.out.println(slea); //To see if there are any differences with packets
        //[CD 00] [6B 00 00 00] [01 00 00 00] [03 00] [00 00 20 03] [F7 03 00 00]
        //[CD 00] [66 00 00 00] [00 00 00 00] [02 00] [00 00 19 01] [00 00 00 00]
        int oid = p.readInt();
        int charPos = p.readInt();
        short stance = p.readShort();
        p.skip(4);
        int skillid = p.readInt();
        Reactor reactor = c.getPlayer().getMap().getReactorByOid(oid);
        if (reactor != null) {
            reactor.hitReactor(true, charPos, stance, skillid, c);
        }
    }
}
