var npcid = 1104103;
const Point = Java.type('java.awt.Point');
var spawnPos = new Point(-2263, -582);

function start(ms) {
    var mapobj = ms.getMap();

    if (!mapobj.containsNPC(npcid)) {
        ms.spawnNpc(npcid, spawnPos, mapobj);
    }
}
