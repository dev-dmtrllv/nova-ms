package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.guild.Guild;
/**
 * @author Xterminator
 */
public final class DenyGuildRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readByte();
        Character cfrom = c.getWorldServer().getPlayerStorage().getCharacterByName(p.readString());
        if (cfrom != null) {
            Guild.answerInvitation(c.getPlayer().getId(), c.getPlayer().getName(), cfrom.getGuildId(), false);
        }
    }
}
