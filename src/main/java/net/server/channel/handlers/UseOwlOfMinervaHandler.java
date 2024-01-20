package net.server.channel.handlers;

import client.Client;
import constants.id.ItemId;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
import tools.Pair;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * @author Ronan
 */
public final class UseOwlOfMinervaHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        List<Pair<Integer, Integer>> owlSearched = c.getWorldServer().getOwlSearchedItems();
        List<Integer> owlLeaderboards;

        if (owlSearched.size() < 5) {
            owlLeaderboards = new LinkedList<>();
            for (int itemId : ItemId.getOwlItems()) {
                owlLeaderboards.add(itemId);
            }
        } else {
            // descending order
            Comparator<Pair<Integer, Integer>> comparator = (p1, p2) -> p2.getRight().compareTo(p1.getRight());

            PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Math.max(1, owlSearched.size()), comparator);
            queue.addAll(owlSearched);

            owlLeaderboards = new LinkedList<>();
            for (int i = 0; i < Math.min(owlSearched.size(), 10); i++) {
                owlLeaderboards.add(queue.remove().getLeft());
            }
        }

        c.sendPacket(PacketCreator.getOwlOpen(owlLeaderboards));
    }
}
