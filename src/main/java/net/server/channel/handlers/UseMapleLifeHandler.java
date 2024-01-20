package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author RonanLana
 */
public class UseMapleLifeHandler extends AbstractPacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        Character player = c.getPlayer();
        long timeNow = currentServerTime();

        if (timeNow - player.getLastUsedCashItem() < 3000) {
            player.dropMessage(5, "Please wait a moment before trying again.");
            c.sendPacket(PacketCreator.sendMapleLifeError(3));
            c.sendPacket(PacketCreator.enableActions());
            return;
        }
        player.setLastUsedCashItem(timeNow);

        String name = p.readString();
        if (Character.canCreateChar(name)) {
            c.sendPacket(PacketCreator.sendMapleLifeCharacterInfo());
        } else {
            c.sendPacket(PacketCreator.sendMapleLifeNameError());
        }
        c.sendPacket(PacketCreator.enableActions());
    }
}
