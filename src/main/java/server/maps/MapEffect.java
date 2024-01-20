package server.maps;

import client.Client;
import net.packet.Packet;
import tools.PacketCreator;

public class MapEffect {
    private final String msg;
    private final int itemId;
    private final boolean active = true;

    public MapEffect(String msg, int itemId) {
        this.msg = msg;
        this.itemId = itemId;
    }

    public final Packet makeDestroyData() {
        return PacketCreator.removeMapEffect();
    }

    public final Packet makeStartData() {
        return PacketCreator.startMapEffect(msg, itemId, active);
    }

    public void sendStartData(Client client) {
        client.sendPacket(makeStartData());
    }
}
