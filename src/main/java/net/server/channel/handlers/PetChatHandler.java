package net.server.channel.handlers;

import client.Client;
import client.autoban.AutobanFactory;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.ChatLogger;
import tools.PacketCreator;

public final class PetChatHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(PetChatHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        int petId = p.readInt();
        p.readInt();
        p.readByte();
        int act = p.readByte();
        byte pet = c.getPlayer().getPetIndex(petId);
        if ((pet < 0 || pet > 3) || (act < 0 || act > 9)) {
            return;
        }
        String text = p.readString();
        if (text.length() > Byte.MAX_VALUE) {
            AutobanFactory.PACKET_EDIT.alert(c.getPlayer(), c.getPlayer().getName() + " tried to packet edit with pets.");
            log.warn("Chr {} tried to send text with length of {}", c.getPlayer().getName(), text.length());
            c.disconnect(true, false);
            return;
        }
        c.getPlayer().getMap().broadcastMessage(c.getPlayer(), PacketCreator.petChat(c.getPlayer().getId(), pet, act, text), true);
        ChatLogger.log(c, "Pet", text);
    }
}
