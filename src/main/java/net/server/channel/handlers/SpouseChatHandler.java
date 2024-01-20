package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.ChatLogger;
import tools.PacketCreator;

public final class SpouseChatHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readString();//recipient
        String msg = p.readString();

        int partnerId = c.getPlayer().getPartnerId();
        if (partnerId > 0) { // yay marriage
            Character spouse = c.getWorldServer().getPlayerStorage().getCharacterById(partnerId);
            if (spouse != null) {
                spouse.sendPacket(PacketCreator.OnCoupleMessage(c.getPlayer().getName(), msg, true));
                c.sendPacket(PacketCreator.OnCoupleMessage(c.getPlayer().getName(), msg, true));
                ChatLogger.log(c, "Spouse", msg);
            } else {
                c.getPlayer().dropMessage(5, "Your spouse is currently offline.");
            }
        } else {
            c.getPlayer().dropMessage(5, "You don't have a spouse.");
        }
    }
}
