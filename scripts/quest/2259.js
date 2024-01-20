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
            qm.sendNext("Ok, meet me at #b#m260020700##k for your information. To reach there, follow #reast#k from here until you reach #rMagatia#k, I will be there. Now go.");
        } else {
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
            if (qm.getMapId() == 260020000) {
                qm.sendNext("Eh you're still here? To reach #b#m260020700##k, follow #reast#k from here until you reach #rMagatia#k, I will be there. Now go.");
                return;
            }

            qm.sendNext("Oh there you are. There're no Meerkat's nearby, so there probably is no eavesdropping around here. Very well, you must be fit to go to the #rMushroom Castle#k. Talk to me once you've got #blevel 30#k.");
            qm.forceCompleteQuest();
        } else if (status == 1) {
            qm.dispose();
        }
    }
}
