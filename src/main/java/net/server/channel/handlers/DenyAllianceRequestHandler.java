package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.guild.Alliance;
/**
 * @author Ronan
 */
public final class DenyAllianceRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readByte();
        String inviterName = p.readString();
        String guildName = p.readString();

        Character chr = c.getWorldServer().getPlayerStorage().getCharacterByName(inviterName);
        if (chr != null) {
            Alliance alliance = chr.getAlliance();
            if (alliance != null) {
                Alliance.answerInvitation(c.getPlayer().getId(), guildName, alliance.getId(), false);
            }
        }
    }
}
