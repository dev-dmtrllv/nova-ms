package net.server.services;

import config.YamlConfig;
/**
 * @author Ronan
 */
public abstract class BaseService {

    protected static int getChannelSchedulerIndex(int mapid) {
        int section = 1000000000 / YamlConfig.config.server.CHANNEL_LOCKS;
        return mapid / section;
    }

    public abstract void dispose();

}
