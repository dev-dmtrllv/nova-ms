package net.server.channel.handlers;

import client.Character;
import client.Character.FameStatus;
import client.Client;
import client.autoban.AutobanFactory;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.PacketCreator;

public final class GiveFameHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(GiveFameHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        Character target = (Character) c.getPlayer().getMap().getMapObject(p.readInt());
        int mode = p.readByte();
        int famechange = 2 * mode - 1;
        Character player = c.getPlayer();
        if (target == null || target.getId() == player.getId() || player.getLevel() < 15) {
            return;
        } else if (famechange != 1 && famechange != -1) {
            AutobanFactory.PACKET_EDIT.alert(c.getPlayer(), c.getPlayer().getName() + " tried to packet edit fame.");
            log.warn("Chr {} tried to fame hack with famechange {}", c.getPlayer().getName(), famechange);
            c.disconnect(true, false);
            return;
        }

        FameStatus status = player.canGiveFame(target);
        if (status == FameStatus.OK) {
            if (target.gainFame(famechange, player, mode)) {
                if (!player.isGM()) {
                    player.hasGivenFame(target);
                }
            } else {
                player.message("Could not process the request, since this character currently has the minimum/maximum level of fame.");
            }
        } else {
            c.sendPacket(PacketCreator.giveFameErrorResponse(status == FameStatus.NOT_TODAY ? 3 : 4));
        }
    }
}
