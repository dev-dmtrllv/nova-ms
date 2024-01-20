package server.quest.requirements;

import client.Character;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestRequirementType;
/**
 * @author Ronan
 */
public class ScriptRequirement extends AbstractQuestRequirement {
    private boolean reqScript;

    public ScriptRequirement(Quest quest, Data data) {
        super(QuestRequirementType.BUFF);
        processData(data);
    }

    @Override
    public void processData(Data data) {
        reqScript = !DataTool.getString(data, "").isEmpty();
    }

    @Override
    public boolean check(Character chr, Integer npcid) {
        return true;
    }

    public boolean get() {
        return reqScript;
    }
}
