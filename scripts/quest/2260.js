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
            qm.sendNext("Once you've got #b2nd job advancement#k, I'll tell you about the #bMushroom Castle#k.");
        } else if (status == 1) {
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
            const GameConstants = Java.type('constants.game.GameConstants');
            if (GameConstants.getJobBranch(qm.getPlayer().getJob()) == 1) {
                qm.sendNext("Eh, didn't you get the #r2nd job advancement#k yet?");
                qm.dispose();
                return;
            }

            qm.sendNext("Okay you seem ready to go to the #bMushroom Castle#k. In #rHenesys#k, climb at the tree fort at #bwest#k then enter a portal over there. On the other area, #rgo west#k. From there, a portal will be readily available to access the #bMushroom Castle#k area.");
            qm.forceCompleteQuest();
        } else if (status == 1) {
            qm.dispose();
        }
    }
}
