package server.quest.requirements;

import client.Character;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestRequirementType;
/**
 * @author Ronan
 */
public class MesoRequirement extends AbstractQuestRequirement {
    private int meso = 0;

    public MesoRequirement(Quest quest, Data data) {
        super(QuestRequirementType.MESO);
        processData(data);
    }

    @Override
    public void processData(Data data) {
        meso = DataTool.getInt(data);
    }
    @Override
    public boolean check(Character chr, Integer npcid) {
        if (chr.getMeso() >= meso) {
            return true;
        } else {
            chr.dropMessage(5, "You don't have enough mesos to complete this quest.");
            return false;
        }
    }
}
