package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.autoban.AutobanFactory;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.Server;
import net.server.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.ChatLogger;
import tools.PacketCreator;

public final class MultiChatHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(MultiChatHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        Character player = c.getPlayer();
        if (player.getAutobanManager().getLastSpam(7) + 200 > currentServerTime()) {
            return;
        }

        int type = p.readByte(); // 0 for buddys, 1 for partys
        int numRecipients = p.readByte();
        int[] recipients = new int[numRecipients];
        for (int i = 0; i < numRecipients; i++) {
            recipients[i] = p.readInt();
        }
        String chattext = p.readString();
        if (chattext.length() > Byte.MAX_VALUE && !player.isGM()) {
            AutobanFactory.PACKET_EDIT.alert(c.getPlayer(), c.getPlayer().getName() + " tried to packet edit chats.");
            log.warn("Chr {} tried to send text with length of {}", c.getPlayer().getName(), chattext.length());
            c.disconnect(true, false);
            return;
        }
        World world = c.getWorldServer();
        if (type == 0) {
            world.buddyChat(recipients, player.getId(), player.getName(), chattext);
            ChatLogger.log(c, "Buddy", chattext);
        } else if (type == 1 && player.getParty() != null) {
            world.partyChat(player.getParty(), chattext, player.getName());
            ChatLogger.log(c, "Party", chattext);
        } else if (type == 2 && player.getGuildId() > 0) {
            Server.getInstance().guildChat(player.getGuildId(), player.getName(), player.getId(), chattext);
            ChatLogger.log(c, "Guild", chattext);
        } else if (type == 3 && player.getGuild() != null) {
            int allianceId = player.getGuild().getAllianceId();
            if (allianceId > 0) {
                Server.getInstance().allianceMessage(allianceId, PacketCreator.multiChat(player.getName(), chattext, 3), player.getId(), -1);
                ChatLogger.log(c, "Ally", chattext);
            }
        }
        player.getAutobanManager().spam(7);
    }
}
