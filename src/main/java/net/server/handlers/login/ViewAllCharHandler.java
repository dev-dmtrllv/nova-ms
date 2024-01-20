package net.server.handlers.login;

import client.Character;
import client.Client;
import config.YamlConfig;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import net.server.Server;
import tools.PacketCreator;

import java.util.*;

public final class ViewAllCharHandler extends AbstractPacketHandler {
    private static final int CHARACTER_LIMIT = 60; // Client will crash if sending 61 or more characters

    @Override
    public final void handlePacket(InPacket p, Client c) {
        try {
            if (!c.canRequestCharlist()) {   // client breaks if the charlist request pops too soon
                c.sendPacket(PacketCreator.showAllCharacter(0, 0));
                return;
            }

            SortedMap<Integer, List<Character>> worldChrs = Server.getInstance().loadAccountCharlist(c.getAccID(), c.getVisibleWorlds());
            worldChrs = limitTotalChrs(worldChrs, CHARACTER_LIMIT);

            padChrsIfNeeded(worldChrs);

            int totalWorlds = worldChrs.size();
            int totalChrs = countTotalChrs(worldChrs);
            c.sendPacket(PacketCreator.showAllCharacter(totalWorlds, totalChrs));

            final boolean usePic = YamlConfig.config.server.ENABLE_PIC && !c.canBypassPic();
            worldChrs.forEach((worldId, chrs) ->
                    c.sendPacket(PacketCreator.showAllCharacterInfo(worldId, chrs, usePic))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static SortedMap<Integer, List<Character>> limitTotalChrs(SortedMap<Integer, List<Character>> worldChrs,
                                                                      int limit) {
        if (countTotalChrs(worldChrs) <= limit) {
            return worldChrs;
        } else {;
            return cutAfterChrLimit(worldChrs, limit);
        }
    }

    private static int countTotalChrs(Map<Integer, List<Character>> worldChrs) {
        return worldChrs.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    private static SortedMap<Integer, List<Character>> cutAfterChrLimit(SortedMap<Integer, List<Character>> worldChrs,
                                                                        int limit) {
        SortedMap<Integer, List<Character>> cappedCopy = new TreeMap<>();
        int runningChrTotal = 0;
        for (Map.Entry<Integer, List<Character>> entry : worldChrs.entrySet()) {
            int worldId = entry.getKey();
            List<Character> chrs = entry.getValue();
            if (runningChrTotal + chrs.size() <= limit) { // Limit not reached, move them all
                runningChrTotal += chrs.size();
                cappedCopy.put(worldId, chrs);
            } else { // Limit would be reached if all chrs were moved. Move just enough to fit within limit.
                int remainingSlots = limit - runningChrTotal;
                List<Character> lastChrs = chrs.subList(0, remainingSlots);
                cappedCopy.put(worldId, lastChrs);
                break;
            }
        }

        return cappedCopy;
    }

    /**
     * If there are more characters than fits the screen (9), and you start scrolling down,
     * the characters on the last row will not appear unless the row is completely filled.
     * Meaning, if there are 1 or 2 characters remaining on the last row, they will not appear.
     *
     * @param totalChrs total amount of characters to display on 'View all characters' screen
     * @return if we need to pad the last row to include the characters that would otherwise not appear
     */
    private static void padChrsIfNeeded(SortedMap<Integer, List<Character>> worldChrs) {
        while (shouldPadLastRow(countTotalChrs(worldChrs))) {
            final List<Character> lastWorldChrs = getLastWorldChrs(worldChrs);
            final Character lastChrForPadding = getLastItem(lastWorldChrs);
            lastWorldChrs.add(lastChrForPadding);
        }
    }

    private static boolean shouldPadLastRow(int totalChrs) {
        boolean shouldScroll = totalChrs > 9;
        boolean isLastRowFilled = totalChrs % 3 == 0;
        return shouldScroll && !isLastRowFilled;
    }

    private static List<Character> getLastWorldChrs(SortedMap<Integer, List<Character>> worldChrs) {
        return worldChrs.get(worldChrs.lastKey());
    }

    private static <T> T getLastItem(List<T> list) {
        Objects.requireNonNull(list);
        return list.get(list.size() - 1);
    }
}
