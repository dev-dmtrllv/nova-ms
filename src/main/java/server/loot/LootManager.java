package server.loot;

import client.Character;
import server.life.MonsterDropEntry;
import server.life.MonsterInformationProvider;
import server.quest.Quest;

import java.util.LinkedList;
import java.util.List;
/**
 * @author Ronan
 */
public class LootManager {

    private static boolean isRelevantDrop(MonsterDropEntry dropEntry, List<Character> players, List<LootInventory> playersInv) {
        int qStartAmount = 0, qCompleteAmount = 0;
        Quest quest = Quest.getInstance(dropEntry.questid);
        if (quest != null) {
            qStartAmount = quest.getStartItemAmountNeeded(dropEntry.itemId);
            qCompleteAmount = quest.getCompleteItemAmountNeeded(dropEntry.itemId);
        }

        //boolean restricted = ItemInformationProvider.getInstance().isPickupRestricted(dropEntry.itemId);
        for (int i = 0; i < players.size(); i++) {
            LootInventory chrInv = playersInv.get(i);

            if (dropEntry.questid > 0) {
                int qItemAmount, chrQuestStatus = players.get(i).getQuestStatus(dropEntry.questid);
                if (chrQuestStatus == 0) {
                    qItemAmount = qStartAmount;
                } else if (chrQuestStatus != 1) {
                    continue;
                } else {
                    qItemAmount = qCompleteAmount;
                }

                // thanks kvmba for noticing quest items with no required amount failing to be detected as such

                int qItemStatus = chrInv.hasItem(dropEntry.itemId, qItemAmount);
                if (qItemStatus == 2) {
                    continue;
                } /*else if (restricted && qItemStatus == 1) {  // one-of-a-kind loots should be available everytime, thanks onechord for noticing
                    continue;
                }*/
            } /*else if (restricted && chrInv.hasItem(dropEntry.itemId, 1) > 0) {   // thanks Conrad, Legalize for noticing eligible loots not being available to drop for non-killer parties
                continue;
            }*/

            return true;
        }

        return false;
    }

    public static List<MonsterDropEntry> retrieveRelevantDrops(int monsterId, List<Character> players) {
        List<MonsterDropEntry> loots = MonsterInformationProvider.getInstance().retrieveEffectiveDrop(monsterId);
        if (loots.isEmpty()) {
            return loots;
        }

        List<LootInventory> playersInv = new LinkedList<>();
        for (Character chr : players) {
            LootInventory lootInv = new LootInventory(chr);
            playersInv.add(lootInv);
        }

        List<MonsterDropEntry> effectiveLoot = new LinkedList<>();
        for (MonsterDropEntry mde : loots) {
            if (isRelevantDrop(mde, players, playersInv)) {
                effectiveLoot.add(mde);
            }
        }

        return effectiveLoot;
    }

}
