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
            if (cm.isQuestStarted(3345)) {
                var progress = cm.getQuestProgressInt(3345);

                if (progress == 3 && cm.haveItem(4031739, 1) && cm.haveItem(4031740, 1) && cm.haveItem(4031741, 1)) {
                    cm.setQuestProgress(3345, 4);
                    cm.gainItem(4031739, -1);
                    cm.gainItem(4031740, -1);
                    cm.gainItem(4031741, -1);

                    cm.sendOk("(As you place the shards a light shines over the circle, repelling whatever omens were brewing inside the artifact.)", 2);
                    cm.dispose();
                } else if (progress < 4) {
                    cm.setQuestProgress(3345, 0);
                    cm.dispose();
                } else {
                    cm.dispose();
                }
            }
        }
    }
}
