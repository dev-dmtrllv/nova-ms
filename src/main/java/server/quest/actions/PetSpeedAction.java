package server.quest.actions;

import client.Character;
import client.Client;
import client.inventory.Pet;
import provider.Data;
import server.quest.Quest;
import server.quest.QuestActionType;
/**
 * @author Ronan
 */
public class PetSpeedAction extends AbstractQuestAction {

    public PetSpeedAction(Quest quest, Data data) {
        super(QuestActionType.PETTAMENESS, quest);
        questID = quest.getId();
    }
    @Override
    public void processData(Data data) {}

    @Override
    public void run(Character chr, Integer extSelection) {
        Client c = chr.getClient();

        Pet pet = chr.getPet(0);   // assuming here only the pet leader will gain owner speed
        if (pet == null) {
            return;
        }

        c.lockClient();
        try {
            pet.addPetAttribute(c.getPlayer(), Pet.PetAttribute.OWNER_SPEED);
        } finally {
            c.unlockClient();
        }

    }
} 
