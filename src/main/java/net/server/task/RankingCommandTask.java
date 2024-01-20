package net.server.task;

import net.server.Server;
/**
 * @author Ronan
 */
public class RankingCommandTask implements Runnable {

    @Override
    public void run() {
        Server.getInstance().updateWorldPlayerRanking();
    }
}
