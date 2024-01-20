
package net.server.channel.handlers;

import client.Client;
import client.autoban.AutobanFactory;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author kevintjuh93
 * <p>
 * Modified by -- Ronan - concurrency protection
 */
public class UseGachaExpHandler extends AbstractPacketHandler {

    @Override
    public void handlePacket(InPacket p, Client c) {

        if (c.tryacquireClient()) {
            try {
                if (c.getPlayer().getGachaExp() <= 0) {
                    AutobanFactory.GACHA_EXP.autoban(c.getPlayer(), "Player tried to redeem GachaEXP, but had none to redeem.");
                }
                c.getPlayer().gainGachaExp();
            } finally {
                c.releaseClient();
            }
        }

        c.sendPacket(PacketCreator.enableActions());
    }
}
