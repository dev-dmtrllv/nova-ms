package net.server.coordinator.matchchecker.listener;

import client.Character;
import constants.string.LanguageConstants;
import net.server.coordinator.matchchecker.AbstractMatchCheckerListener;
import net.server.coordinator.matchchecker.MatchCheckerListenerRecipe;
import net.server.world.PartyCharacter;
import scripting.npc.NPCConversationManager;
import scripting.npc.NPCScriptManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/**
 * @author Ronan
 */
public class MatchCheckerCPQChallenge implements MatchCheckerListenerRecipe {

    public static AbstractMatchCheckerListener loadListener() {
        return (new MatchCheckerCPQChallenge()).getListener();
    }

    private static Character getChallenger(int leaderid, Set<Character> matchPlayers) {
        Character leader = null;
        for (Character chr : matchPlayers) {
            if (chr.getId() == leaderid && chr.getClient() != null) {
                leader = chr;
                break;
            }
        }

        return leader;
    }

    @Override
    public AbstractMatchCheckerListener getListener() {
        return new AbstractMatchCheckerListener() {

            @Override
            public void onMatchCreated(Character leader, Set<Character> nonLeaderMatchPlayers, String message) {
                NPCConversationManager cm = leader.getClient().getCM();
                int npcid = cm.getNpc();

                Character ldr = null;
                for (Character chr : nonLeaderMatchPlayers) {
                    ldr = chr;
                    break;
                }

                Character chr = leader;

                List<PartyCharacter> chrMembers = new LinkedList<>();
                for (PartyCharacter mpc : chr.getParty().getMembers()) {
                    if (mpc.isOnline()) {
                        chrMembers.add(mpc);
                    }
                }

                if (message.contentEquals("cpq1")) {
                    NPCScriptManager.getInstance().start("cpqchallenge", ldr.getClient(), npcid, chrMembers);
                } else {
                    NPCScriptManager.getInstance().start("cpqchallenge2", ldr.getClient(), npcid, chrMembers);
                }

                cm.sendOk(LanguageConstants.getMessage(chr, LanguageConstants.CPQChallengeRoomSent));
            }

            @Override
            public void onMatchAccepted(int leaderid, Set<Character> matchPlayers, String message) {
                Character chr = getChallenger(leaderid, matchPlayers);

                Character ldr = null;
                for (Character ch : matchPlayers) {
                    if (ch != chr) {
                        ldr = ch;
                        break;
                    }
                }

                if (message.contentEquals("cpq1")) {
                    ldr.getClient().getCM().startCPQ(chr, ldr.getMapId() + 1);
                } else {
                    ldr.getClient().getCM().startCPQ2(chr, ldr.getMapId() + 1);
                }

                ldr.getParty().setEnemy(chr.getParty());
                chr.getParty().setEnemy(ldr.getParty());
                chr.setChallenged(false);
            }

            @Override
            public void onMatchDeclined(int leaderid, Set<Character> matchPlayers, String message) {
                Character chr = getChallenger(leaderid, matchPlayers);
                chr.dropMessage(5, LanguageConstants.getMessage(chr, LanguageConstants.CPQChallengeRoomDenied));
            }

            @Override
            public void onMatchDismissed(int leaderid, Set<Character> matchPlayers, String message) {}
        };
    }
}
