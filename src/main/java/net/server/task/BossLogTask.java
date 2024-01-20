package net.server.task;

import server.expeditions.ExpeditionBossLog;
/**
 * @author Ronan
 */
public class BossLogTask implements Runnable {

    @Override
    public void run() {
        ExpeditionBossLog.resetBossLogTable();
    }
}
