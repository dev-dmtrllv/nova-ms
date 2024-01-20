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
            qm.sendNext("You want to use a Wolf as a mount, but you don't have a #bWolf saddle#k? Why, I have just the fine solution for you! Come here in #bEl Nath#k first, I shall teach you how to mount a wolf as an extra.");
        } else if (status == 1) {
            qm.sendNext("Once here, hunt for #r50 #t4000048##k then bring them to me.");
        } else if (status == 2) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
