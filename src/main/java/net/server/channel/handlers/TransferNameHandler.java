
package net.server.channel.handlers;

import client.Character;
import client.Client;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.DatabaseConnection;
import tools.PacketCreator;

import java.sql.*;
import java.util.Calendar;

import static java.util.concurrent.TimeUnit.DAYS;
/**
 * @author Ronan
 * @author Ubaware
 */
public final class TransferNameHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt(); //cid
        int birthday = p.readInt();
        if (!CashOperationHandler.checkBirthday(c, birthday)) {
            c.sendPacket(PacketCreator.showCashShopMessage((byte) 0xC4));
            c.sendPacket(PacketCreator.enableActions());
            return;
        }
        if (!YamlConfig.config.server.ALLOW_CASHSHOP_NAME_CHANGE) {
            c.sendPacket(PacketCreator.sendNameTransferRules(4));
            return;
        }
        Character chr = c.getPlayer();
        if (chr.getLevel() < 10) {
            c.sendPacket(PacketCreator.sendNameTransferRules(4));
            return;
        } else if (c.getTempBanCalendar() != null && c.getTempBanCalendar().getTimeInMillis() + DAYS.toMillis(30) < Calendar.getInstance().getTimeInMillis()) {
            c.sendPacket(PacketCreator.sendNameTransferRules(2));
            return;
        }
        //sql queries
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT completionTime FROM namechanges WHERE characterid=?")) { //double check, just in case
            ps.setInt(1, chr.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp completedTimestamp = rs.getTimestamp("completionTime");
                if (completedTimestamp == null) { //has pending name request
                    c.sendPacket(PacketCreator.sendNameTransferRules(1));
                    return;
                } else if (completedTimestamp.getTime() + YamlConfig.config.server.NAME_CHANGE_COOLDOWN > System.currentTimeMillis()) {
                    c.sendPacket(PacketCreator.sendNameTransferRules(3));
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        c.sendPacket(PacketCreator.sendNameTransferRules(0));
    }
}
