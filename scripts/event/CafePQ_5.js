/**
 * @author: Ronan
 * @event: Cafe PQ 5
 */

var isPq = true;
var minPlayers = 3, maxPlayers = 6;
var minLevel = 21, maxLevel = 120;
var entryMap = 196000000;
var exitMap = 193000000;
var recruitMap = 193000000;

var minMapId = 196000000;
var maxMapId = 196010000;

var eventMaps = [196000000, 196010000];

var eventTime = 45;         // 45 minutes
var couponsNeeded = 500;    // total of coupons to complete the event

const maxLobbies = 1;

function init() {
    setEventRequirements();
}

function getMaxLobbies() {
    return maxLobbies;
}

function setEventRequirements() {
    var reqStr = "";

    reqStr += "\r\n    Number of players: ";
    if (maxPlayers - minPlayers >= 1) {
        reqStr += minPlayers + " ~ " + maxPlayers;
    } else {
        reqStr += minPlayers;
    }

    reqStr += "\r\n    Level range: ";
    if (maxLevel - minLevel >= 1) {
        reqStr += minLevel + " ~ " + maxLevel;
    } else {
        reqStr += minLevel;
    }

    reqStr += "\r\n    Time limit: ";
    reqStr += eventTime + " minutes";

    em.setProperty("party", reqStr);
}

function setEventExclusives(eim) {
    var itemSet = [4001007];
    eim.setExclusiveItems(itemSet);
}

function setEventRewards(eim) {
    var itemSet, itemQty, evLevel, expStages;

    evLevel = 1;    //Rewards at clear PQ
    itemSet = [4001012];
    itemQty = [1];
    eim.setEventRewards(evLevel, itemSet, itemQty);

    expStages = [25000];    //bonus exp given on CLEAR stage signal
    eim.setEventClearStageExp(expStages);
}

function getEligibleParty(party) {      //selects, from the given party, the team that is allowed to attempt this event
    var eligible = [];
    var hasLeader = false;

    if (party.size() > 0) {
        var partyList = party.toArray();

        for (var i = 0; i < party.size(); i++) {
            var ch = partyList[i];

            if (ch.getMapId() == recruitMap && ch.getLevel() >= minLevel && ch.getLevel() <= maxLevel) {
                if (ch.isLeader()) {
                    hasLeader = true;
                }
                eligible.push(ch);
            }
        }
    }

    if (!(hasLeader && eligible.length >= minPlayers && eligible.length <= maxPlayers)) {
        eligible = [];
    }
    return Java.to(eligible, Java.type('net.server.world.PartyCharacter[]'));
}

function setup(level, lobbyid) {
    var eim = em.newInstance("Lan5_" + lobbyid);
    eim.setProperty("level", level);
    eim.setProperty("stage", "0");

    eim.setIntProperty("couponsNeeded", couponsNeeded);

    var i;
    for (i = 0; i < eventMaps.length; i++) {
        var mapObj = eim.getInstanceMap(eventMaps[i]);
        mapObj.resetPQ(level);
        mapObj.toggleDrops();
        mapObj.instanceMapForceRespawn();
    }

    respawnStages(eim);
    eim.startEventTimer(eventTime * 60000);
    setEventRewards(eim);
    setEventExclusives(eim);
    return eim;
}

function afterSetup(eim) {}

function respawnStages(eim) {
    var i;
    for (i = 0; i < eventMaps.length; i++) {
        eim.getInstanceMap(eventMaps[i]).instanceMapRespawn();
    }

    eim.schedule("respawnStages", 15 * 1000);
}

function playerEntry(eim, player) {
    var map = eim.getMapInstance(entryMap);
    player.changeMap(map, map.getPortal(0));
}

function scheduledTimeout(eim) {
    end(eim);
}

function playerUnregistered(eim, player) {}

function playerExit(eim, player) {
    eim.unregisterPlayer(player);
    player.changeMap(exitMap, 0);
}

function playerLeft(eim, player) {
    if (!eim.isEventCleared()) {
        playerExit(eim, player);
    }
}

function changedMap(eim, player, mapid) {
    if (mapid < minMapId || mapid > maxMapId) {
        if (eim.isEventTeamLackingNow(true, minPlayers, player)) {
            eim.unregisterPlayer(player);
            end(eim);
        } else {
            eim.unregisterPlayer(player);
        }
    }
}

function changedLeader(eim, leader) {
    var mapid = leader.getMapId();
    if (!eim.isEventCleared() && (mapid < minMapId || mapid > maxMapId)) {
        end(eim);
    }
}

function playerDead(eim, player) {}

function playerRevive(eim, player) { // player presses ok on the death pop up.
    if (eim.isEventTeamLackingNow(true, minPlayers, player)) {
        eim.unregisterPlayer(player);
        end(eim);
    } else {
        eim.unregisterPlayer(player);
    }
}

function playerDisconnected(eim, player) {
    if (eim.isEventTeamLackingNow(true, minPlayers, player)) {
        eim.unregisterPlayer(player);
        end(eim);
    } else {
        eim.unregisterPlayer(player);
    }
}

function leftParty(eim, player) {
    if (eim.isEventTeamLackingNow(false, minPlayers, player)) {
        end(eim);
    } else {
        playerLeft(eim, player);
    }
}

function disbandParty(eim) {
    if (!eim.isEventCleared()) {
        end(eim);
    }
}

function monsterValue(eim, mobId) {
    return 1;
}

function end(eim) {
    var party = eim.getPlayers();
    for (var i = 0; i < party.size(); i++) {
        playerExit(eim, party.get(i));
    }
    eim.dispose();
}

function giveRandomEventReward(eim, player) {
    eim.giveEventReward(player);
}

function clearPQ(eim) {
    eim.stopEventTimer();
    eim.setEventCleared();
    eim.giveEventPlayersStageReward(1);

    var i;
    for (i = 0; i < eventMaps.length; i++) {
        eim.getInstanceMap(eventMaps[i]).killAllMonstersNotFriendly();
        eim.showClearEffect(eventMaps[i]);
    }
}

function getDroppedQuantity(mob) {
    if (mob.getLevel() > 65) {
        return 5;
    } else if (mob.getLevel() > 40) {
        return 2;
    } else {
        return 1;
    }
}

function monsterKilled(mob, eim) {
    try {
        if (eim.isEventCleared()) {
            return;
        }

        var mapObj = mob.getMap();
        const Item = Java.type('client.inventory.Item');
        var itemObj = new Item(4001007, 0, getDroppedQuantity(mob));
        var dropper = eim.getPlayers().get(0);

        mapObj.spawnItemDrop(mob, dropper, itemObj, mob.getPosition(), true, false);

    } catch (err) {
    } // PQ not started yet
}

function allMonstersDead(eim) {}

function cancelSchedule() {}

function dispose(eim) {}
