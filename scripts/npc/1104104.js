var npcid = 1104104;
var baseJob = 15;
var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            if (Math.floor(cm.getJobId() / 100) != baseJob) {
                cm.sendOk("Hello there, #h0#. Are you helping us finding the intruder? He is not in this area, I've already searched here.");
                cm.dispose();
                return;
            }

            cm.sendOk("Darn, you found me! Then, there's only one way out! Let's fight, like #rBlack Wings#k should!");
        } else if (status == 1) {
            var mapobj = cm.getMap();
            var npcpos = mapobj.getMapObject(cm.getNpcObjectId()).getPosition();

            spawnMob(npcpos.x, npcpos.y, 9001009, mapobj);
            mapobj.destroyNPC(npcid);
            cm.dispose();
        }
    }
}

function spawnMob(x, y, id, map) {
    if (map.getMonsterById(id) != null) {
        return;
    }

    const LifeFactory = Java.type('server.life.LifeFactory');
    const Point = Java.type('java.awt.Point');
    var mob = LifeFactory.getMonster(id);
    map.spawnMonsterOnGroundBelow(mob, new Point(x, y));
}
