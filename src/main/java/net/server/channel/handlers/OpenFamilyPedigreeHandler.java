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
public final class OpenFamilyPedigreeHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_FAMILY_SYSTEM) {
            return;
        }
        Character target = c.getChannelServer().getPlayerStorage().getCharacterByName(p.readString());
        if (target != null && target.getFamily() != null) {
            c.sendPacket(PacketCreator.showPedigree(target.getFamilyEntry()));
        }
    }
}
