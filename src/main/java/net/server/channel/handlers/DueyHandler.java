package net.server.channel.handlers;

import client.Client;
import client.processor.npc.DueyProcessor;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public final class DueyHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_DUEY) {
            c.sendPacket(PacketCreator.enableActions());
            return;
        }

        byte operation = p.readByte();
        if (operation == DueyProcessor.Actions.TOSERVER_RECV_ITEM.getCode()) { // on click 'O' Button, thanks inhyuk
            DueyProcessor.dueySendTalk(c, false);
        } else if (operation == DueyProcessor.Actions.TOSERVER_SEND_ITEM.getCode()) {
            byte inventId = p.readByte();
            short itemPos = p.readShort();
            short amount = p.readShort();
            int mesos = p.readInt();
            String recipient = p.readString();
            boolean quick = p.readByte() != 0;
            String message = quick ? p.readString() : null;

            DueyProcessor.dueySendItem(c, inventId, itemPos, amount, mesos, message, recipient, quick);
        } else if (operation == DueyProcessor.Actions.TOSERVER_REMOVE_PACKAGE.getCode()) {
            int packageid = p.readInt();

            DueyProcessor.dueyRemovePackage(c, packageid, true);
        } else if (operation == DueyProcessor.Actions.TOSERVER_CLAIM_PACKAGE.getCode()) {
            int packageid = p.readInt();

            DueyProcessor.dueyClaimPackage(c, packageid);
        } else if (operation == DueyProcessor.Actions.TOSERVER_CLAIM_PACKAGE.getCode()) {
            DueyProcessor.dueySendTalk(c, false);
        }
    }
}
