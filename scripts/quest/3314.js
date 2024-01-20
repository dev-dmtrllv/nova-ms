var status = -1;

function isPillUsed(ch) {
    const BuffStat = Java.type('client.BuffStat');
    return ch.getBuffSource(BuffStat.HPREC) == 2022198;
}

function end(mode, type, selection) {
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
            if (isPillUsed(qm.getPlayer())) {
                if (qm.canHoldAll([2050004, 2022224], [10, 20])) {
                    qm.sendNext("You did take my experiments. Hmm, so THAT is the result of it, hehehehe... Ok, take that as compensation will you? And oh, you can #rspew that#k right away (#bright-click on the pill icon at the top-right corner of the screen#k), no worries.");

                    qm.gainExp(12500);
                    qm.gainItem(2050004, 10);

                    var i = Math.floor(Math.random() * 5);
                    qm.gainItem(2022224 + i, 10);

                    qm.forceCompleteQuest();
                } else {
                    qm.sendNext("Huh, your inventory is full. Free some spaces on your USE first.");
                }
            } else {
                qm.sendNext("You seem pretty normal, don't you? I can't detect any possible effect from my experiment on you. Go take the pill I asked you to take and show me the effects, will you?");
            }
        } else if (status == 1) {
            qm.dispose();
        }
    }
}
