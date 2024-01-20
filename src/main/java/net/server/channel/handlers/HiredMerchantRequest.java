package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.inventory.ItemFactory;
import constants.game.GameConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.MapObject;
import server.maps.MapObjectType;
import server.maps.PlayerShop;
import server.maps.Portal;
import tools.PacketCreator;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
/**
 * @author XoticStory
 */
public final class HiredMerchantRequest extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();

        try {
            for (MapObject mmo : chr.getMap().getMapObjectsInRange(chr.getPosition(), 23000, Arrays.asList(MapObjectType.HIRED_MERCHANT, MapObjectType.PLAYER))) {
                if (mmo instanceof Character mc) {

                    PlayerShop shop = mc.getPlayerShop();
                    if (shop != null && shop.isOwner(mc)) {
                        chr.sendPacket(PacketCreator.getMiniRoomError(13));
                        return;
                    }
                } else {
                    chr.sendPacket(PacketCreator.getMiniRoomError(13));
                    return;
                }
            }

            Point cpos = chr.getPosition();
            Portal portal = chr.getMap().findClosestTeleportPortal(cpos);
            if (portal != null && portal.getPosition().distance(cpos) < 120.0) {
                chr.sendPacket(PacketCreator.getMiniRoomError(10));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GameConstants.isFreeMarketRoom(chr.getMapId())) {
            if (!chr.hasMerchant()) {
                try {
                    if (ItemFactory.MERCHANT.loadItems(chr.getId(), false).isEmpty() && chr.getMerchantMeso() == 0) {
                        c.sendPacket(PacketCreator.hiredMerchantBox());
                    } else {
                        chr.sendPacket(PacketCreator.retrieveFirstMessage());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                chr.dropMessage(1, "You already have a store open.");
            }
        } else {
            chr.dropMessage(1, "You cannot open your hired merchant here.");
        }
    }
}
