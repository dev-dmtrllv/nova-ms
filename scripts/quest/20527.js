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
            var mount = qm.getPlayer().getMount();

            if (mount != null && mount.getLevel() >= 3) {
                qm.forceCompleteQuest();
                qm.sendNext("Alright, I'll get you started in how to train Mimio, the next step for Mimianas. When you're ready, talk to me again.");
            } else {
                qm.sendNext("It looks like your Mimiana haven't reached #rlevel 3#k yet. Please train it a bit more before trying to advance it.");
            }
        } else if (status == 1) {
            qm.dispose();
        }
    }
}
