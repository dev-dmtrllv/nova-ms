package server.quest.actions;

import client.Character;
import client.Client;
import client.inventory.Pet;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestActionType;
/**
 * @author Ronan
 */
public class PetTamenessAction extends AbstractQuestAction {
    int tameness;

    public PetTamenessAction(Quest quest, Data data) {
        super(QuestActionType.PETTAMENESS, quest);
        questID = quest.getId();
        processData(data);
    }
    @Override
    public void processData(Data data) {
        tameness = DataTool.getInt(data);
    }

    @Override
    public void run(Character chr, Integer extSelection) {
        Client c = chr.getClient();

        Pet pet = chr.getPet(0);   // assuming here only the pet leader will gain tameness
        if (pet == null) {
            return;
        }

        c.lockClient();
        try {
            pet.gainTamenessFullness(chr, tameness, 0, 0);
        } finally {
            c.unlockClient();
        }
    }
} 
