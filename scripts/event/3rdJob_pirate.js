/**
 * @Author Ronan
 * 3rd Job Event - Pirate
 **/

var entryMap = 108010500;
var exitMap = 105070200;

var minMapId = 108010500;
var maxMapId = 108010501;

var eventTime = 20; //20 minutes

const maxLobbies = 7;

function getMaxLobbies() {
    return maxLobbies;
}

function init() {
    em.setProperty("noEntry", "false");
}

function playerEntry(eim, player) {
    eim.getInstanceMap(maxMapId).resetPQ(1);

    player.changeMap(entryMap, 0);
    em.setProperty("noEntry", "true");

    const PacketCreator = Java.type('tools.PacketCreator');
    player.sendPacket(PacketCreator.getClock(eventTime * 60));
    eim.startEventTimer(eventTime * 60000);
}

function setup(level, lobbyid) {
    var eim = em.newInstance("3rdJob_pirate_" + lobbyid);
    eim.setProperty("level", level);
    eim.setProperty("boss", "0");

    return eim;
}

function playerUnregistered(eim, player) {}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);
    eim.dispose();
    em.setProperty("noEntry", "false");
}

function scheduledTimeout(eim) {
    var player = eim.getPlayers().get(0);
    playerExit(eim, eim.getPlayers().get(0));
    player.changeMap(exitMap);
}

function playerDisconnected(eim, player) {
    playerExit(eim, player);
}

function clear(eim) {
    var player = eim.getPlayers().get(0);
    eim.unregisterPlayer(player);
    player.changeMap(exitMap);

    eim.dispose();
    em.setProperty("noEntry", "false");
}

function changedMap(eim, chr, mapid) {
    if (mapid < minMapId || mapid > maxMapId) {
        playerExit(eim, chr);
    }
}

function monsterKilled(mob, eim) {}

function monsterValue(eim, mobId) {
    return 1;
}

function allMonstersDead(eim) {}

function cancelSchedule() {}

function dispose() {}


// ---------- FILLER FUNCTIONS ----------

function disbandParty(eim, player) {}

function afterSetup(eim) {}

function changedLeader(eim, leader) {}

function leftParty(eim, player) {}

function clearPQ(eim) {}
