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
            qm.sendNext("Aran, Lith have been caught off guard. We are under attack! Get here ASAP.");
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
            qm.sendNext("Aran, thank you very much! Somehow the Puppeteer managed to bypass the security of Lith Harbor. He was trying to seek revenge because of the other day. Luckily, you came by. Nicely done!");
        } else if (status == 1) {
            qm.sendNext("I will teach you the #rPolearm Mastery#k skill, to reward your actions here. You will be able to improve your accuracy and the overall mastery of your polearm arts.");
        } else if (status == 2) {
            qm.gainExp(8000);
            qm.teachSkill(21100000, 0, 20, -1); // polearm mastery

            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}
