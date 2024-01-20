var npcid = 1104104;
const Point = Java.type('java.awt.Point');
var spawnPos = new Point(372, 70);

function start(ms) {
    var mapobj = ms.getMap();

    if (!mapobj.containsNPC(npcid)) {
        ms.spawnNpc(npcid, spawnPos, mapobj);
    }
}
