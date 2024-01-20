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

                if (progress == 0) {
                    cm.setQuestProgress(3345, 1);
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
