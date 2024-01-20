package net.server.coordinator.matchchecker;

import client.Character;

import java.util.Set;
/**
 * @author Ronan
 */
public interface AbstractMatchCheckerListener {
    void onMatchCreated(Character leader, Set<Character> nonLeaderMatchPlayers, String message);
    void onMatchAccepted(int leaderid, Set<Character> matchPlayers, String message);
    void onMatchDeclined(int leaderid, Set<Character> matchPlayers, String message);
    void onMatchDismissed(int leaderid, Set<Character> matchPlayers, String message);
}
