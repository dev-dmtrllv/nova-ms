package server.gachapon;
/**
 * @author Alan (SharpAceX)
 */

public abstract class GachaponItems {

    public abstract int[] getCommonItems();
    public abstract int[] getUncommonItems();
    public abstract int[] getRareItems();

    private final int[] commonItems;
    private final int[] uncommonItems;
    private final int[] rareItems;

    public GachaponItems() {
        this.commonItems = getCommonItems();
        this.uncommonItems = getUncommonItems();
        this.rareItems = getRareItems();
    }

    public final int[] getItems(int tier) {
        switch (tier) {
        case 0:
            return commonItems;
        case 1:
            return uncommonItems;
        case 2:
            return rareItems;
        default:
            return null;
        }
    }
}
