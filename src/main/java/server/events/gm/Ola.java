package server.events.gm;

import client.Character;
import constants.id.MapId;
import server.TimerManager;
import tools.PacketCreator;

import java.util.concurrent.ScheduledFuture;
/**
 * @author kevintjuh93
 */
public class Ola {
    private final Character chr;
    private long time = 0;
    private long timeStarted = 0;
    private ScheduledFuture<?> schedule = null;

    public Ola(final Character chr) {
        this.chr = chr;
        this.schedule = TimerManager.getInstance().schedule(() -> {
            if (MapId.isOlaOla(chr.getMapId())) {
                chr.changeMap(chr.getMap().getReturnMap());
            }
            resetTimes();
        }, 360000);
    }

    public void startOla() { // TODO: Messages
        chr.getMap().startEvent();
        chr.sendPacket(PacketCreator.getClock(360));
        this.timeStarted = System.currentTimeMillis();
        this.time = 360000;

        chr.getMap().getPortal("join00").setPortalStatus(true);
        chr.sendPacket(PacketCreator.serverNotice(0, "The portal has now opened. Press the up arrow key at the portal to enter."));
    }

    public boolean isTimerStarted() {
        return time > 0 && timeStarted > 0;
    }

    public long getTime() {
        return time;
    }

    public void resetTimes() {
        this.time = 0;
        this.timeStarted = 0;
        schedule.cancel(false);
    }

    public long getTimeLeft() {
        return time - (System.currentTimeMillis() - timeStarted);
    }
}
