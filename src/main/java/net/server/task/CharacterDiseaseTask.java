package net.server.task;

import net.server.Server;
/**
 * @author Ronan
 * @info Thread responsible for announcing other players diseases when one enters into a map
 */
public class CharacterDiseaseTask implements Runnable {
    @Override
    public void run() {
        Server serv = Server.getInstance();

        serv.updateCurrentTime();
        serv.runAnnouncePlayerDiseasesSchedule();
    }
}
