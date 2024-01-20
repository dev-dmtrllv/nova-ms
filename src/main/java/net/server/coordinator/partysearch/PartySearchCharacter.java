package net.server.coordinator.partysearch;

import client.Character;

import java.lang.ref.WeakReference;
/**
 * @author Ronan
 */
public class PartySearchCharacter {

    private final WeakReference<Character> player;
    private final int level;
    private boolean queued;

    public PartySearchCharacter(Character chr) {
        player = new WeakReference(chr);
        level = chr.getLevel();
        queued = true;
    }

    @Override
    public String toString() {
        Character chr = player.get();
        return chr == null ? "[empty]" : chr.toString();
    }

    public Character callPlayer(int leaderid, int callerMapid) {
        Character chr = player.get();
        if (chr == null || !PartySearchCoordinator.isInVicinity(callerMapid, chr.getMapId())) {
            return null;
        }

        if (chr.hasDisabledPartySearchInvite(leaderid)) {
            return null;
        }

        queued = false;
        if (chr.isLoggedinWorld() && chr.getParty() == null) {
            return chr;
        } else {
            return null;
        }
    }

    public Character getPlayer() {
        return player.get();
    }

    public int getLevel() {
        return level;
    }

    public boolean isQueued() {
        return queued;
    }

}
