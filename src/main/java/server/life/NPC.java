package server.life;

import client.Client;
import server.ShopFactory;
import server.maps.MapObjectType;
import tools.PacketCreator;

public class NPC extends AbstractLoadedLife {
    private final NPCStats stats;

    public NPC(int id, NPCStats stats) {
        super(id);
        this.stats = stats;
    }

    public boolean hasShop() {
        return ShopFactory.getInstance().getShopForNPC(getId()) != null;
    }

    public void sendShop(Client c) {
        ShopFactory.getInstance().getShopForNPC(getId()).sendShop(c);
    }

    @Override
    public void sendSpawnData(Client client) {
        client.sendPacket(PacketCreator.spawnNPC(this));
        client.sendPacket(PacketCreator.spawnNPCRequestController(this, true));
    }

    @Override
    public void sendDestroyData(Client client) {
        client.sendPacket(PacketCreator.removeNPCController(getObjectId()));
        client.sendPacket(PacketCreator.removeNPC(getObjectId()));
    }

    @Override
    public MapObjectType getType() {
        return MapObjectType.NPC;
    }

    public String getName() {
        return stats.getName();
    }
}
