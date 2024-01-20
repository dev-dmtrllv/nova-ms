package net.server.channel.handlers;

import client.BuffStat;
import client.Character;
import client.Client;
import net.packet.InPacket;

public final class TouchMonsterDamageHandler extends AbstractDealDamageHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        if (chr.getEnergyBar() == 15000 || chr.getBuffedValue(BuffStat.BODY_PRESSURE) != null) {
            applyAttack(parseDamage(p, chr, false, false), c.getPlayer(), 1);
        }
    }
}
