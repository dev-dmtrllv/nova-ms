package server.life;

import provider.DataProvider;
import provider.DataProviderFactory;
import provider.wz.WZFiles;
/**
 * @author RonanLana
 */
public class PlayerNPCFactory {
    private static final DataProvider npcData = DataProviderFactory.getDataProvider(WZFiles.NPC);

    public synchronized static boolean isExistentScriptid(int scriptid) {
        return npcData.getData(scriptid + ".img") != null;
    }
}
