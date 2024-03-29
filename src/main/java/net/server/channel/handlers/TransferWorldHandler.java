
package net.server.channel.handlers;

import client.Character;
import client.Client;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.Server;
import tools.DatabaseConnection;
import tools.PacketCreator;

import java.sql.*;
/**
 * @author Ronan
 * @author Ubaware
 */
public final class TransferWorldHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt(); //cid
        int birthday = p.readInt();
        if (!CashOperationHandler.checkBirthday(c, birthday)) {
            c.sendPacket(PacketCreator.showCashShopMessage((byte) 0xC4));
            c.sendPacket(PacketCreator.enableActions());
            return;
        }
        Character chr = c.getPlayer();
        if (!YamlConfig.config.server.ALLOW_CASHSHOP_WORLD_TRANSFER || Server.getInstance().getWorldsSize() <= 1) {
            c.sendPacket(PacketCreator.sendWorldTransferRules(9, c));
            return;
        }
        int worldTransferError = chr.checkWorldTransferEligibility();
        if (worldTransferError != 0) {
            c.sendPacket(PacketCreator.sendWorldTransferRules(worldTransferError, c));
            return;
        }
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT completionTime FROM worldtransfers WHERE characterid=?")) {
            ps.setInt(1, chr.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp completedTimestamp = rs.getTimestamp("completionTime");
                if (completedTimestamp == null) { //has pending world transfer
                    c.sendPacket(PacketCreator.sendWorldTransferRules(6, c));
                    return;
                } else if (completedTimestamp.getTime() + YamlConfig.config.server.WORLD_TRANSFER_COOLDOWN > System.currentTimeMillis()) {
                    c.sendPacket(PacketCreator.sendWorldTransferRules(7, c));
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        c.sendPacket(PacketCreator.sendWorldTransferRules(0, c));
    }
}
