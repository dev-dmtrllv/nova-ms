/**
 * @Author Ronan
 * Event - Balrog Quest
 **/

var entryMap = 910520000;
var exitMap = 105100100;

var minMapId = 910520000;
var maxMapId = 910520000;

var eventTime = 10;     //10 minutes

const maxLobbies = 7;

function getMaxLobbies() {
    return maxLobbies;
}

function init() {
    em.setProperty("noEntry", "false");
}

function setup(level, lobbyid) {
    var eim = em.newInstance("BalrogQuest_" + lobbyid);
    eim.setProperty("level", level);
    eim.setProperty("boss", "0");

    return eim;
}

function respawnStages(eim) {}

function afterSetup(eim) {}

function playerEntry(eim, player) {
    var mapObj = eim.getInstanceMap(entryMap);

    mapObj.resetPQ(1);
    mapObj.instanceMapForceRespawn();
    mapObj.closeMapSpawnPoints();
    respawnStages(eim);

    player.changeMap(entryMap, 1);
    em.setProperty("noEntry", "true");

    const PacketCreator = Java.type('tools.PacketCreator');
    player.sendPacket(PacketCreator.getClock(eventTime * 60));
    eim.startEventTimer(eventTime * 60000);
}

function playerUnregistered(eim, player) {}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);
    eim.dispose();
    em.setProperty("noEntry", "false");
}

function scheduledTimeout(eim) {
    var player = eim.getPlayers().get(0);
    playerExit(eim, player);
    player.changeMap(exitMap);
}

function playerDisconnected(eim, player) {
    playerExit(eim, player);
}

function changedMap(eim, chr, mapid) {
    if (mapid < minMapId || mapid > maxMapId) {
        playerExit(eim, chr);
    }
}

function isBalrog(mob) {
    return mob.getId() == 9300326;
}

function monsterKilled(mob, eim) {
    if (isBalrog(mob)) {
        const Point = Java.type('java.awt.Point');
        eim.spawnNpc(1061015, new Point(0, 115), mob.getMap());
    }
}

function monsterValue(eim, mobId) {
    return 1;
}

function allMonstersDead(eim) {}

function cancelSchedule() {}

function dispose() {}


// ---------- FILLER FUNCTIONS ----------

function disbandParty(eim, player) {}

function changedLeader(eim, leader) {}

function leftParty(eim, player) {}

function clearPQ(eim) {}
