/**
 * @Author Ronan
 * Event - Jonathan's Test Quest
 **/

var entryMap = 912020000;
var exitMap = 120000102;

var minMapId = 912020000;
var maxMapId = 912020000;

var eventTime = 2; //2 minutes

const maxLobbies = 7;

function getMaxLobbies() {
    return maxLobbies;
}

function init() {
    em.setProperty("noEntry", "false");
}

function setup(level, lobbyid) {
    var eim = em.newInstance("4jaerial_" + lobbyid);
    eim.setProperty("level", level);
    eim.setProperty("boss", "0");
    eim.setProperty("canLeave", "0");

    eim.getInstanceMap(entryMap).resetPQ(level);
    eim.getInstanceMap(entryMap).shuffleReactors();

    respawnStages(eim);
    eim.startEventTimer(eventTime * 60000);
    return eim;
}

function afterSetup(eim) {}

function respawnStages(eim) {}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(entryMap);
    player.changeMap(map, map.getPortal(0));
}

function playerUnregistered(eim, player) {}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);
    eim.dispose();
    em.setProperty("noEntry", "false");
}

function playerLeft(eim, player) {}

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

function clearPQ(eim) {
    eim.stopEventTimer();
    eim.setEventCleared();
}

function monsterKilled(mob, eim) {}

function leftParty(eim, player) {}

function disbandParty(eim) {}

function monsterValue(eim, mobId) {
    return 1;
}

function allMonstersDead(eim) {}

function cancelSchedule() {}

function dispose() {}


// ---------- FILLER FUNCTIONS ----------

function changedLeader(eim, leader) {}
