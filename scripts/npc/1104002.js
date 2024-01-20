var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        var mapobj = cm.getMap();

        if (mode == 0 && type > 0) {
            cm.getPlayer().dropMessage(5, "Eleanor: Oh, lost the Empress and still challenging us? Now you've done it! Prepare yourself!!!");

            const LifeFactory = Java.type('server.life.LifeFactory');
            const Point = Java.type('java.awt.Point');
            mapobj.spawnMonsterOnGroundBelow(LifeFactory.getMonster(9001010), new Point(850, 0));
            mapobj.destroyNPC(1104002);

            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            if (!cm.isQuestStarted(20407)) {
                cm.sendOk("... Knight, you still #bseem unsure to face this fight#k, don't you? There's no grace in challenging someone when they are still not mentally ready for the battle. Talk your peace to that big clumsy bird of yours, maybe it'll put some guts on you.");
                cm.dispose();
                return;
            }

            cm.sendAcceptDecline("Hahahahaha! This place's Empress is already under my domain, that's surely a great advance on the #bBlack Wings#k' overthrow towards Maple World... And you, there? Still wants to face us? Or, better yet, since you seem strong enough to be quite a supplementary reinforcement at our service, #rwill you meet our expectations and fancy joining us#k since there's nothing more you can do?");
        } else if (status == 1) {
            cm.sendOk("Heh, cowards have no place on the #rBlack Mage's#k army. Begone!");
            cm.dispose();
        }
    }
}
