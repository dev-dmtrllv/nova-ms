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
            qm.sendAcceptDecline("I may have an idea of the whereabouts of Dr. De Lang. Are you ready to be transported to the area?");
        } else if (status == 1) {
            qm.forceStartQuest();
            qm.warp(926120200, 1);
            qm.dispose();
        }
    }
}
