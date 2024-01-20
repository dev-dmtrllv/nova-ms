package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.processor.action.PetAutopotProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.ItemInformationProvider;
import server.StatEffect;

public final class PetAutoPotHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readByte();
        p.readLong();
        p.readInt();
        short slot = p.readShort();
        int itemId = p.readInt();

        Character chr = c.getPlayer();
        StatEffect stat = ItemInformationProvider.getInstance().getItemEffect(itemId);
        if (stat.getHp() > 0 || stat.getHpRate() > 0.0) {
            float estimatedHp = ((float) chr.getHp()) / chr.getMaxHp();
            chr.setAutopotHpAlert(estimatedHp + 0.05f);
        }

        if (stat.getMp() > 0 || stat.getMpRate() > 0.0) {
            float estimatedMp = ((float) chr.getMp()) / chr.getMaxMp();
            chr.setAutopotMpAlert(estimatedMp + 0.05f);
        }

        PetAutopotProcessor.runAutopotAction(c, slot, itemId);
    }

}
