var npcid = 1104100;
const Point = Java.type('java.awt.Point');
var spawnPos = new Point(2830, 78);

function start(ms) {
    var mapobj = ms.getMap();

    if (!mapobj.containsNPC(npcid)) {
        ms.spawnNpc(npcid, spawnPos, mapobj);
    }
}
