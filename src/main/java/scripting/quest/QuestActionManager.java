package scripting.quest;

import client.Client;
import scripting.npc.NPCConversationManager;
import server.ItemInformationProvider;
import server.quest.Quest;
import server.quest.actions.ExpAction;
import server.quest.actions.MesoAction;
/**
 * @author RMZero213
 */
public class QuestActionManager extends NPCConversationManager {
    private final boolean start; // this is if the script in question is start or end
    private final int quest;

    public QuestActionManager(Client c, int quest, int npc, boolean start) {
        super(c, npc, null);
        this.quest = quest;
        this.start = start;
    }

    public int getQuest() {
        return quest;
    }

    public boolean isStart() {
        return start;
    }

    @Override
    public void dispose() {
        QuestScriptManager.getInstance().dispose(this, getClient());
    }

    public boolean forceStartQuest() {
        return forceStartQuest(quest);
    }

    public boolean forceCompleteQuest() {
        return forceCompleteQuest(quest);
    }

    // For compatibility with some older scripts...
    public void startQuest() {
        forceStartQuest();
    }

    // For compatibility with some older scripts...
    public void completeQuest() {
        forceCompleteQuest();
    }

    @Override
    public void gainExp(int gain) {
        ExpAction.runAction(getPlayer(), gain);
    }

    @Override
    public void gainMeso(int gain) {
        MesoAction.runAction(getPlayer(), gain);
    }

    public String getMedalName() {  // usable only for medal quests (id 299XX)
        Quest q = Quest.getInstance(quest);
        return ItemInformationProvider.getInstance().getName(q.getMedalRequirement());
    }
}
