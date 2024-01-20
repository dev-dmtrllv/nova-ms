package client.inventory.manipulator;

import client.inventory.Item;
import constants.inventory.ItemConstants;
/**
 * @author RonanLana
 */
public class KarmaManipulator {
    private static short getKarmaFlag(Item item) {
        return item.getItemType() == 1 ? ItemConstants.KARMA_EQP : ItemConstants.KARMA_USE;
    }

    public static boolean hasKarmaFlag(Item item) {
        short karmaFlag = getKarmaFlag(item);
        return (item.getFlag() & karmaFlag) == karmaFlag;
    }

    public static void toggleKarmaFlagToUntradeable(Item item) {
        short karmaFlag = getKarmaFlag(item);
        short flag = item.getFlag();

        if ((flag & karmaFlag) == karmaFlag) {
            flag ^= karmaFlag;
            flag |= ItemConstants.UNTRADEABLE;

            item.setFlag(flag);
        }
    }

    public static void setKarmaFlag(Item item) {
        short karmaFlag = getKarmaFlag(item);
        short flag = item.getFlag();

        flag |= karmaFlag;
        flag &= (0xFFFFFFFF ^ ItemConstants.UNTRADEABLE);
        item.setFlag(flag);
    }
}
