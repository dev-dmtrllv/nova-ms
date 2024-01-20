package net.server.services.task.channel;

import config.YamlConfig;
import net.server.services.BaseScheduler;
import net.server.services.BaseService;
/**
 * @author Ronan
 */
public class OverallService extends BaseService {   // thanks Alex for suggesting a refactor over the several channel schedulers unnecessarily populating the Channel class

    private final OverallScheduler[] channelSchedulers = new OverallScheduler[YamlConfig.config.server.CHANNEL_LOCKS];

    public OverallService() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            channelSchedulers[i] = new OverallScheduler();
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            if (channelSchedulers[i] != null) {
                channelSchedulers[i].dispose();
                channelSchedulers[i] = null;
            }
        }
    }

    public void registerOverallAction(int mapid, Runnable runAction, long delay) {
        channelSchedulers[getChannelSchedulerIndex(mapid)].registerDelayedAction(runAction, delay);
    }

    public void forceRunOverallAction(int mapid, Runnable runAction) {
        channelSchedulers[getChannelSchedulerIndex(mapid)].forceRunDelayedAction(runAction);
    }
    public class OverallScheduler extends BaseScheduler {

        public void registerDelayedAction(Runnable runAction, long delay) {
            registerEntry(runAction, runAction, delay);
        }

        public void forceRunDelayedAction(Runnable runAction) {
            interruptEntry(runAction);
        }

    }

}
