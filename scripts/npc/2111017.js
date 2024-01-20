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
            if (cm.isQuestStarted(3339)) {
                var progress = cm.getQuestProgressInt(23339, 1);

                if (progress == 3) {
                    cm.sendGetText("The pipe reacts as the water starts flowing. A secret compartment with a keypad shows up. #bPassword#k!");
                } else if (progress == 0) {
                    cm.setQuestProgress(23339, 1, 1);
                    cm.dispose();
                } else if (progress < 3) {
                    cm.setQuestProgress(23339, 1, 0);
                    cm.dispose();
                } else {
                    cm.warp(261000001, 1);
                    cm.dispose();
                }
            } else {
                if (cm.isQuestCompleted(3339)) {
                    cm.warp(261000001, 1);
                }

                cm.dispose();
            }
        } else if (status == 1) {
            if (cm.getText() == "my love Phyllia") {
                cm.setQuestProgress(23339, 1, 4);
                cm.warp(261000001, 1);
                cm.dispose();
            } else {
                cm.sendOk("#rWrong!");
                cm.dispose();
            }
        }
    }
}
