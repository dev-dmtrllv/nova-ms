package net.server.coordinator.partysearch;

import client.Character;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * @author Ronan
 */
public class PartySearchEchelon {
    private final Lock psRLock;
    private final Lock psWLock;

    private final Map<Integer, WeakReference<Character>> echelon = new HashMap<>(20);

    public PartySearchEchelon() {
        ReadWriteLock partySearchLock = new ReentrantReadWriteLock(true);
        this.psRLock = partySearchLock.readLock();
        this.psWLock = partySearchLock.writeLock();
    }

    public List<Character> exportEchelon() {
        psWLock.lock();     // reversing read/write actually could provide a lax yet sure performance/precision trade-off here
        try {
            List<Character> players = new ArrayList<>(echelon.size());

            for (WeakReference<Character> chrRef : echelon.values()) {
                Character chr = chrRef.get();
                if (chr != null) {
                    players.add(chr);
                }
            }

            echelon.clear();
            return players;
        } finally {
            psWLock.unlock();
        }
    }

    public void attachPlayer(Character chr) {
        psRLock.lock();
        try {
            echelon.put(chr.getId(), new WeakReference<>(chr));
        } finally {
            psRLock.unlock();
        }
    }

    public boolean detachPlayer(Character chr) {
        psRLock.lock();
        try {
            return echelon.remove(chr.getId()) != null;
        } finally {
            psRLock.unlock();
        }
    }

}
