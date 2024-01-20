var npcid = 1104101;
const Point = Java.type('java.awt.Point');
var spawnPos = new Point(3395, -322);

function start(ms) {
    var mapobj = ms.getMap();

    if (!mapobj.containsNPC(npcid)) {
        ms.spawnNpc(npcid, spawnPos, mapobj);
    }
}
