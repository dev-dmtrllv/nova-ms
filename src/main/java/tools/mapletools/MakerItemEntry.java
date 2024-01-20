package tools.mapletools;

import java.util.List;
/**
 * @author RonanLana
 */
public class MakerItemEntry {
    public int id = -1;
    public int itemid = -1;
    public int reqLevel = -1;
    public int reqMakerLevel = -1;
    public int reqItem = -1;
    public int reqMeso = -1;
    public int reqEquip = -1;
    public int catalyst = -1;
    public int quantity = -1;
    public int tuc = -1;

    public int recipeCount = -1;
    public int recipeItem = -1;

    public List<int[]> recipeList = null;
    public List<int[]> randomList = null;

    MakerItemEntry(int id, int itemid, int reqLevel, int reqMakerLevel, int reqItem, int reqMeso, int reqEquip, int catalyst, int quantity, int tuc, int recipeCount, int recipeItem, List<int[]> recipeList, List<int[]> randomList) {
        this.id = id;
        this.itemid = itemid;
        this.reqLevel = reqLevel;
        this.reqMakerLevel = reqMakerLevel;
        this.reqItem = reqItem;
        this.reqMeso = reqMeso;
        this.reqEquip = reqEquip;
        this.catalyst = catalyst;
        this.quantity = quantity;
        this.tuc = tuc;

        this.recipeCount = recipeCount;
        this.recipeItem = recipeItem;

        this.recipeList = recipeList;
        this.randomList = randomList;
    }
}
