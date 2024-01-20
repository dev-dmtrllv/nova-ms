package server.maps;

import client.Character;
import client.Client;
import client.SkillFactory;
import tools.PacketCreator;

import java.awt.*;
/**
 * @author Jan
 */
public class Summon extends AbstractAnimatedMapObject {
    private final Character owner;
    private final byte skillLevel;
    private final int skill;
    private int hp;
    private final SummonMovementType movementType;

    public Summon(Character owner, int skill, Point pos, SummonMovementType movementType) {
        this.owner = owner;
        this.skill = skill;
        this.skillLevel = owner.getSkillLevel(SkillFactory.getSkill(skill));
        if (skillLevel == 0) {
            throw new RuntimeException();
        }

        this.movementType = movementType;
        setPosition(pos);
    }

    @Override
    public void sendSpawnData(Client client) {
        client.sendPacket(PacketCreator.spawnSummon(this, false));
    }

    @Override
    public void sendDestroyData(Client client) {
        client.sendPacket(PacketCreator.removeSummon(this, true));
    }

    public Character getOwner() {
        return owner;
    }

    public int getSkill() {
        return skill;
    }

    public int getHP() {
        return hp;
    }

    public void addHP(int delta) {
        this.hp += delta;
    }

    public SummonMovementType getMovementType() {
        return movementType;
    }

    public boolean isStationary() {
        return (skill == 3111002 || skill == 3211002 || skill == 5211001 || skill == 13111004);
    }

    public byte getSkillLevel() {
        return skillLevel;
    }

    @Override
    public MapObjectType getType() {
        return MapObjectType.SUMMON;
    }

    public final boolean isPuppet() {
        switch (skill) {
            case 3111002:
            case 3211002:
            case 13111004:
                return true;
        }
        return false;
    }
}
