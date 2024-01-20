/**
 * @Author Ronan
 * Event - Kyrin's Test Quest
 **/

var entryMap = 912010000;
var exitMap = 120000101;

var minMapId = 912010000;
var maxMapId = 912010200;

var eventTime = 4; //4 minutes

const maxLobbies = 7;

function getMaxLobbies() {
    return maxLobbies;
}

function init() {
    em.setProperty("noEntry", "false");
}

function setup(level, lobbyid) {
    var eim = em.newInstance("4jship_" + lobbyid);
    eim.setProperty("level", level);
    eim.setProperty("boss", "0");
    eim.setProperty("canLeave", "0");

    eim.getInstanceMap(entryMap).resetPQ(level);

    respawnStages(eim);
    eim.startEventTimer(eventTime * 60000);
    eim.schedule("playerCanLeave", 1 * 60000);
    eim.schedule("playerSurvived", 2 * 60000);
    return eim;
}

function afterSetup(eim) {}

function respawnStages(eim) {}

function playerCanLeave(eim) {
    eim.setIntProperty("canLeave", 1);
}

function playerSurvived(eim) {
    if (eim.getLeader().isAlive()) {
        eim.setIntProperty("canLeave", 2);
        eim.dropMessage(5, "Kyrin: You have passed the test. Now for the closing part... Are you able reach the exit over there?");
    } else {
        eim.dropMessage(5, "Kyrin: You have failed the test. Aww, don't have such a sad face, just try it again later, ok?");
    }
}

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

    var player = eim.getPlayers().get(0);
    eim.unregisterPlayer(player);
    player.changeMap(exitMap);

    eim.dispose();
    em.setProperty("noEntry", "false");
}

function monsterKilled(mob, eim) {}

function leftParty(eim, player) {}

function disbandParty(eim) {}

function monsterValue(eim, mobId) {
    return 1;
}

function friendlyKilled(mob, eim) {
    if (em.getProperty("noEntry") != "false") {
        var player = eim.getPlayers().get(0);
        playerExit(eim, player);
        player.changeMap(exitMap);
    }
}

function allMonstersDead(eim) {}

function cancelSchedule() {}

function dispose() {}


// ---------- FILLER FUNCTIONS ----------

function changedLeader(eim, leader) {}
