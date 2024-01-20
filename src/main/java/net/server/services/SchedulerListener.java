package net.server.services;

import java.util.List;
/**
 * @author Ronan
 */
public interface SchedulerListener {
    void removedScheduledEntries(List<Object> entries, boolean update);
}
