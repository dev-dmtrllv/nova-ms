package net.server.coordinator.world;

import config.YamlConfig;
import scripting.event.EventInstanceManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Ronan
 */
public class EventRecallCoordinator {

    private final static EventRecallCoordinator instance = new EventRecallCoordinator();

    public static EventRecallCoordinator getInstance() {
        return instance;
    }

    private final ConcurrentHashMap<Integer, EventInstanceManager> eventHistory = new ConcurrentHashMap<>();

    private static boolean isRecallableEvent(EventInstanceManager eim) {
        return eim != null && !eim.isEventDisposed() && !eim.isEventCleared();
    }

    public EventInstanceManager recallEventInstance(int characterId) {
        EventInstanceManager eim = eventHistory.remove(characterId);
        return isRecallableEvent(eim) ? eim : null;
    }

    public void storeEventInstance(int characterId, EventInstanceManager eim) {
        if (YamlConfig.config.server.USE_ENABLE_RECALL_EVENT && isRecallableEvent(eim)) {
            eventHistory.put(characterId, eim);
        }
    }

    public void manageEventInstances() {
        if (!eventHistory.isEmpty()) {
            List<Integer> toRemove = new LinkedList<>();

            for (Entry<Integer, EventInstanceManager> eh : eventHistory.entrySet()) {
                if (!isRecallableEvent(eh.getValue())) {
                    toRemove.add(eh.getKey());
                }
            }

            for (Integer r : toRemove) {
                eventHistory.remove(r);
            }
        }
    }
}
