/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import tools.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UnBanCommand extends Command {
    {
        setDescription("Unban a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !unban <playername>");
            return;
        }

        try (Connection con = DatabaseConnection.getConnection()) {
            int aid = Character.getAccountIdByName(params[0]);

            try (PreparedStatement p = con.prepareStatement("UPDATE accounts SET banned = -1 WHERE id = " + aid)) {
                p.executeUpdate();
            }

            try (PreparedStatement p = con.prepareStatement("DELETE FROM ipbans WHERE aid = " + aid)) {
                p.executeUpdate();
            }

            try (PreparedStatement p = con.prepareStatement("DELETE FROM macbans WHERE aid = " + aid)) {
                p.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            player.message("Failed to unban " + params[0]);
            return;
        }
        player.message("Unbanned " + params[0]);
    }
}
