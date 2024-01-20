var npcid = 1104102;
const Point = Java.type('java.awt.Point');
var spawnPos = new Point(500, -522);

function start(ms) {
    var mapobj = ms.getMap();

    if (!mapobj.containsNPC(npcid)) {
        ms.spawnNpc(npcid, spawnPos, mapobj);
    }
}
