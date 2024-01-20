package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.coordinator.world.InviteCoordinator;
import net.server.coordinator.world.InviteCoordinator.InviteResultType;
import net.server.coordinator.world.InviteCoordinator.InviteType;
import tools.PacketCreator;

public final class DenyPartyRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readByte();
        String[] cname = p.readString().split("PS: ");

        Character cfrom = c.getChannelServer().getPlayerStorage().getCharacterByName(cname[cname.length - 1]);
        if (cfrom != null) {
            Character chr = c.getPlayer();

            if (InviteCoordinator.answerInvite(InviteType.PARTY, chr.getId(), cfrom.getPartyId(), false).result == InviteResultType.DENIED) {
                chr.updatePartySearchAvailability(chr.getParty() == null);
                cfrom.sendPacket(PacketCreator.partyStatusMessage(23, chr.getName()));
            }
        }
    }
}
