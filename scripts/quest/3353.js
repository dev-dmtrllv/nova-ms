var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            qm.dispose();
            return;
        }

        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            qm.sendNext("I see. De Lang wants to stop the Huroids from causing more destruction, but the societies would like to get him on jail at once. So that's why he hid himself there.");
        } else if (status == 1) {
            qm.sendAcceptDecline("In that case, go there again and hear more details from De Lang, will you?");
        } else if (status == 2) {
            qm.warp(926120200, 1);

            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
