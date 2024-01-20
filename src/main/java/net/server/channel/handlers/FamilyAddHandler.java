package net.server.channel.handlers;

import client.Character;
import client.Client;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.coordinator.world.InviteCoordinator;
import net.server.coordinator.world.InviteCoordinator.InviteType;
import tools.PacketCreator;
/**
 * @author Jay Estrella
 * @author Ubaware
 */
public final class FamilyAddHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_FAMILY_SYSTEM) {
            return;
        }
        String toAdd = p.readString();
        Character addChr = c.getChannelServer().getPlayerStorage().getCharacterByName(toAdd);
        Character chr = c.getPlayer();
        if (addChr == null) {
            c.sendPacket(PacketCreator.sendFamilyMessage(65, 0));
        } else if (addChr == chr) { //only possible through packet editing/client editing i think?
            c.sendPacket(PacketCreator.enableActions());
        } else if (addChr.getMap() != chr.getMap() || (addChr.isHidden()) && chr.gmLevel() < addChr.gmLevel()) {
            c.sendPacket(PacketCreator.sendFamilyMessage(69, 0));
        } else if (addChr.getLevel() <= 10) {
            c.sendPacket(PacketCreator.sendFamilyMessage(77, 0));
        } else if (Math.abs(addChr.getLevel() - chr.getLevel()) > 20) {
            c.sendPacket(PacketCreator.sendFamilyMessage(72, 0));
        } else if (addChr.getFamily() != null && addChr.getFamily() == chr.getFamily()) { //same family
            c.sendPacket(PacketCreator.enableActions());
        } else if (InviteCoordinator.hasInvite(InviteType.FAMILY, addChr.getId())) {
            c.sendPacket(PacketCreator.sendFamilyMessage(73, 0));
        } else if (chr.getFamily() != null && addChr.getFamily() != null && addChr.getFamily().getTotalGenerations() + chr.getFamily().getTotalGenerations() > YamlConfig.config.server.FAMILY_MAX_GENERATIONS) {
            c.sendPacket(PacketCreator.sendFamilyMessage(76, 0));
        } else {
            InviteCoordinator.createInvite(InviteType.FAMILY, chr, addChr, addChr.getId());
            addChr.getClient().sendPacket(PacketCreator.sendFamilyInvite(chr.getId(), chr.getName()));
            chr.dropMessage("The invite has been sent.");
            c.sendPacket(PacketCreator.enableActions());
        }
    }
}
