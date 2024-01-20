package net.server.services.task.channel;

import config.YamlConfig;
import net.server.services.BaseScheduler;
import net.server.services.BaseService;
/**
 * @author Ronan
 */
public class MobClearSkillService extends BaseService {

    private final MobClearSkillScheduler[] mobClearSkillSchedulers = new MobClearSkillScheduler[YamlConfig.config.server.CHANNEL_LOCKS];

    public MobClearSkillService() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            mobClearSkillSchedulers[i] = new MobClearSkillScheduler();
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            if (mobClearSkillSchedulers[i] != null) {
                mobClearSkillSchedulers[i].dispose();
                mobClearSkillSchedulers[i] = null;
            }
        }
    }

    public void registerMobClearSkillAction(int mapid, Runnable runAction, long delay) {
        mobClearSkillSchedulers[getChannelSchedulerIndex(mapid)].registerClearSkillAction(runAction, delay);
    }

    private class MobClearSkillScheduler extends BaseScheduler {

        public void registerClearSkillAction(Runnable runAction, long delay) {
            registerEntry(runAction, runAction, delay);
        }

    }

}
