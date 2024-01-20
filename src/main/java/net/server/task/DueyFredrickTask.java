package net.server.task;

import client.processor.npc.DueyProcessor;
import client.processor.npc.FredrickProcessor;
/**
 * @author Ronan
 */
public class DueyFredrickTask implements Runnable {
    private final FredrickProcessor fredrickProcessor;

    public DueyFredrickTask(FredrickProcessor fredrickProcessor) {
        this.fredrickProcessor = fredrickProcessor;
    }

    @Override
    public void run() {
        fredrickProcessor.runFredrickSchedule();
        DueyProcessor.runDueyExpireSchedule();
    }
}
