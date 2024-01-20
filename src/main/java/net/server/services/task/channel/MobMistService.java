package net.server.services.task.channel;

import config.YamlConfig;
import net.server.services.BaseScheduler;
import net.server.services.BaseService;
/**
 * @author Ronan
 */
public class MobMistService extends BaseService {

    private final MobMistScheduler[] mobMistSchedulers = new MobMistScheduler[YamlConfig.config.server.CHANNEL_LOCKS];

    public MobMistService() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            mobMistSchedulers[i] = new MobMistScheduler();
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            if (mobMistSchedulers[i] != null) {
                mobMistSchedulers[i].dispose();
                mobMistSchedulers[i] = null;
            }
        }
    }

    public void registerMobMistCancelAction(int mapid, Runnable runAction, long delay) {
        mobMistSchedulers[getChannelSchedulerIndex(mapid)].registerMistCancelAction(runAction, delay);
    }

    private class MobMistScheduler extends BaseScheduler {

        public void registerMistCancelAction(Runnable runAction, long delay) {
            registerEntry(runAction, runAction, delay);
        }

    }

}
