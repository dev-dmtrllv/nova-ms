package net.server.services.task.channel;

import config.YamlConfig;
import net.server.services.BaseScheduler;
import net.server.services.BaseService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author Ronan
 */
public class MobAnimationService extends BaseService {

    private final MobAnimationScheduler[] mobAnimationSchedulers = new MobAnimationScheduler[YamlConfig.config.server.CHANNEL_LOCKS];

    public MobAnimationService() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            mobAnimationSchedulers[i] = new MobAnimationScheduler();
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < YamlConfig.config.server.CHANNEL_LOCKS; i++) {
            if (mobAnimationSchedulers[i] != null) {
                mobAnimationSchedulers[i].dispose();
                mobAnimationSchedulers[i] = null;
            }
        }
    }

    public boolean registerMobOnAnimationEffect(int mapid, int mobHash, long delay) {
        return mobAnimationSchedulers[getChannelSchedulerIndex(mapid)].registerAnimationMode(mobHash, delay);
    }

    // do nothing
    private static final Runnable r = () -> {
    };

    private class MobAnimationScheduler extends BaseScheduler {
        Set<Integer> onAnimationMobs = new HashSet<>(1000);
        private final Lock animationLock = new ReentrantLock(true);

        public MobAnimationScheduler() {
            super.addListener((toRemove, update) -> {
                animationLock.lock();
                try {
                    for (Object hashObj : toRemove) {
                        Integer mobHash = (Integer) hashObj;
                        onAnimationMobs.remove(mobHash);
                    }
                } finally {
                    animationLock.unlock();
                }
            });
        }

        public boolean registerAnimationMode(Integer mobHash, long animationTime) {
            animationLock.lock();
            try {
                if (onAnimationMobs.contains(mobHash)) {
                    return false;
                }

                registerEntry(mobHash, r, animationTime);
                onAnimationMobs.add(mobHash);
                return true;
            } finally {
                animationLock.unlock();
            }
        }

        @Override
        public void dispose() {
            super.dispose();
        }

    }

}
