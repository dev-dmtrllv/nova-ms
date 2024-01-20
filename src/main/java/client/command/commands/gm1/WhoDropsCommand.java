/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm1;

import client.Character;
import client.Client;
import client.command.Command;
import constants.id.NpcId;
import server.ItemInformationProvider;
import server.life.MonsterInformationProvider;
import tools.DatabaseConnection;
import tools.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

public class WhoDropsCommand extends Command {
    {
        setDescription("Show what drops an item.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.dropMessage(5, "Please do @whodrops <item name>");
            return;
        }

        if (c.tryacquireClient()) {
            try {
                String searchString = player.getLastCommandMessage();
                String output = "";
                Iterator<Pair<Integer, String>> listIterator = ItemInformationProvider.getInstance().getItemDataByName(searchString).iterator();
                if (listIterator.hasNext()) {
                    int count = 1;
                    while (listIterator.hasNext() && count <= 3) {
                        Pair<Integer, String> data = listIterator.next();
                        output += "#b" + data.getRight() + "#k is dropped by:\r\n";
                        try (Connection con = DatabaseConnection.getConnection();
                             PreparedStatement ps = con.prepareStatement("SELECT dropperid FROM drop_data WHERE itemid = ? LIMIT 50")) {
                            ps.setInt(1, data.getLeft());

                            try (ResultSet rs = ps.executeQuery()) {
                                while (rs.next()) {
                                    String resultName = MonsterInformationProvider.getInstance().getMobNameFromId(rs.getInt("dropperid"));
                                    if (resultName != null) {
                                        output += resultName + ", ";
                                    }
                                }
                            }
                        } catch (Exception e) {
                            player.dropMessage(6, "There was a problem retrieving the required data. Please try again.");
                            e.printStackTrace();
                            return;
                        }
                        output += "\r\n\r\n";
                        count++;
                    }
                } else {
                    player.dropMessage(5, "The item you searched for doesn't exist.");
                    return;
                }

                c.getAbstractPlayerInteraction().npcTalk(NpcId.MAPLE_ADMINISTRATOR, output);
            } finally {
                c.releaseClient();
            }
        } else {
            player.dropMessage(5, "Please wait a while for your request to be processed.");
        }
    }
}
