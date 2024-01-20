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
            qm.sendNext("Aran, ever since the Puppeteer's ambush on me, I've been thinking it is dangerous to have the #b#t4032323##k around here by myself. So, I need you to deliver the gem to #r#p1201000##k, in Rien, she will know what to do with it.");
        } else if (status == 1) {
            if (!qm.canHold(4032323, 1)) {
                qm.sendNext("Please free a slot on your ETC inventory before receiving the item.");
                qm.dispose();
                return;
            }

            if (!qm.haveItem(4032323, 1)) {
                qm.gainItem(4032323, 1);
            }
            qm.forceStartQuest();
            qm.dispose();
        }
    }
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
            if (qm.haveItem(4032323, 1)) {
                qm.sendNext("#r#p1002104##k sent the #b#t4032323##k here for safety? Thank goodness, indeed here the gem will be safer than anywhere on Victoria Island. Thank you, #b#h0##k.");
            } else {
                qm.dispose();
            }
        } else if (status == 1) {
            qm.gainItem(4032323, -1);
            qm.gainExp(6037);
            qm.forceCompleteQuest();

            qm.dispose();
        }
    }
}
