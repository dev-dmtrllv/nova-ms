/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import server.TimerManager;
import tools.DatabaseConnection;
import tools.PacketCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BanCommand extends Command {
    {
        setDescription("Ban a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !ban <IGN> <Reason> (Please be descriptive)");
            return;
        }
        String ign = params[0];
        String reason = joinStringFrom(params, 1);
        Character target = c.getChannelServer().getPlayerStorage().getCharacterByName(ign);
        if (target != null) {
            String readableTargetName = Character.makeMapleReadable(target.getName());
            String ip = target.getClient().getRemoteAddress();
            //Ban ip
            try (Connection con = DatabaseConnection.getConnection()) {
                if (ip.matches("/[0-9]{1,3}\\..*")) {
                    try (PreparedStatement ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?, ?)")) {
                        ps.setString(1, ip);
                        ps.setString(2, String.valueOf(target.getClient().getAccID()));

                        ps.executeUpdate();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                c.getPlayer().message("Error occured while banning IP address");
                c.getPlayer().message(target.getName() + "'s IP was not banned: " + ip);
            }
            target.getClient().banMacs();
            reason = c.getPlayer().getName() + " banned " + readableTargetName + " for " + reason + " (IP: " + ip + ") " + "(MAC: " + c.getMacs() + ")";
            target.ban(reason);
            target.yellowMessage("You have been banned by #b" + c.getPlayer().getName() + " #k.");
            target.yellowMessage("Reason: " + reason);
            c.sendPacket(PacketCreator.getGMEffect(4, (byte) 0));
            final Character rip = target;
            TimerManager.getInstance().schedule(() -> rip.getClient().disconnect(false, false), 5000); //5 Seconds
            Server.getInstance().broadcastMessage(c.getWorld(), PacketCreator.serverNotice(6, "[RIP]: " + ign + " has been banned."));
        } else if (Character.ban(ign, reason, false)) {
            c.sendPacket(PacketCreator.getGMEffect(4, (byte) 0));
            Server.getInstance().broadcastMessage(c.getWorld(), PacketCreator.serverNotice(6, "[RIP]: " + ign + " has been banned."));
        } else {
            c.sendPacket(PacketCreator.getGMEffect(6, (byte) 1));
        }
    }
}
