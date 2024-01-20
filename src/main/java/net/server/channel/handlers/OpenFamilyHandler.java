package net.server.channel.handlers;

import client.Character;
import client.Client;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Ubaware
 */
public final class OpenFamilyHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_FAMILY_SYSTEM) {
            return;
        }
        Character chr = c.getPlayer();
        c.sendPacket(PacketCreator.getFamilyInfo(chr.getFamilyEntry()));
    }
}
