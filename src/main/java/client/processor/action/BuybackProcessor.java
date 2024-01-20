package client.processor.action;    // thanks Alex for pointing out some package structures containing broad modules

import client.Character;
import client.Client;
import server.maps.MapleMap;
import tools.PacketCreator;
/**
 * @author RonanLana
 */
public class BuybackProcessor {

    public static void processBuyback(Client c) {
        Character chr = c.getPlayer();
        boolean buyback;

        c.lockClient();
        try {
            buyback = !chr.isAlive() && chr.couldBuyback();
        } finally {
            c.unlockClient();
        }

        if (buyback) {
            String jobString;
            switch (chr.getJobStyle()) {
                case WARRIOR:
                    jobString = "warrior";
                    break;

                case MAGICIAN:
                    jobString = "magician";
                    break;

                case BOWMAN:
                    jobString = "bowman";
                    break;

                case THIEF:
                    jobString = "thief";
                    break;

                case BRAWLER:
                case GUNSLINGER:
                    jobString = "pirate";
                    break;

                default:
                    jobString = "beginner";
            }

            chr.healHpMp();
            chr.purgeDebuffs();
            chr.broadcastStance(chr.isFacingLeft() ? 5 : 4);

            MapleMap map = chr.getMap();
            map.broadcastMessage(PacketCreator.playSound("Buyback/" + jobString));
            map.broadcastMessage(PacketCreator.earnTitleMessage(chr.getName() + " just bought back into the game!"));

            chr.sendPacket(PacketCreator.showBuybackEffect());
            map.broadcastMessage(chr, PacketCreator.showForeignBuybackEffect(chr.getId()), false);
        }
    }
}
