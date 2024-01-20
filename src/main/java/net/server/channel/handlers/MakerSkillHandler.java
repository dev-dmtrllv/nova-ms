package net.server.channel.handlers;

import client.Client;
import client.processor.action.MakerProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;
/**
 * @author Jay Estrella, Ronan
 */
public final class MakerSkillHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        MakerProcessor.makerAction(p, c);
    }
}
