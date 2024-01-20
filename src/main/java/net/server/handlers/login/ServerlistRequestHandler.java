package net.server.handlers.login;

import client.Client;
import constants.game.GameConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.Server;
import net.server.world.World;
import tools.PacketCreator;

import java.util.List;

public final class ServerlistRequestHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        Server server = Server.getInstance();
        List<World> worlds = server.getWorlds();
        c.requestedServerlist(worlds.size());

        for (World world : worlds) {
            c.sendPacket(PacketCreator.getServerList(world.getId(), GameConstants.WORLD_NAMES[world.getId()], world.getFlag(), world.getEventMessage(), world.getChannels()));
        }
        c.sendPacket(PacketCreator.getEndOfServerList());
        c.sendPacket(PacketCreator.selectWorld(0));//too lazy to make a check lol
        c.sendPacket(PacketCreator.sendRecommended(server.worldRecommendedList()));
    }
}
