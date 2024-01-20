package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.Trade;
import server.Trade.TradeResult;
import server.maps.Portal;
import tools.PacketCreator;

public final class ChangeMapSpecialHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readByte();
        String startwp = p.readString();
        p.readShort();
        Portal portal = c.getPlayer().getMap().getPortal(startwp);
        if (portal == null || c.getPlayer().portalDelay() > currentServerTime() || c.getPlayer().getBlockedPortals().contains(portal.getScriptName())) {
            c.sendPacket(PacketCreator.enableActions());
            return;
        }
        if (c.getPlayer().isChangingMaps() || c.getPlayer().isBanned()) {
            c.sendPacket(PacketCreator.enableActions());
            return;
        }
        if (c.getPlayer().getTrade() != null) {
            Trade.cancelTrade(c.getPlayer(), TradeResult.UNSUCCESSFUL_ANOTHER_MAP);
        }
        portal.enterPortal(c);
    }
}
