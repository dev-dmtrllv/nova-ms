/* Aran lv 200 mount quest
 */

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
            qm.sendNext("Oh, this befriended wolf of yours... I sense some hidden powers hidden behind his furs, you see. Wat'cha say, master, if I awaken it's hidden power?", 9);
        } else if (status == 1) {
            qm.sendNextPrev("Wait, can you do that?", 3);
        } else if (status == 2) {
            qm.sendAcceptDecline("Astonished, huh? Does all that time frozen in the glacier hindered your senses as well? Why, of course! Tell me when you're ready!", 9);
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
            if (!qm.haveItemWithId(1902017, false)) {
                qm.sendNext("You will have to unequip the wolf first before going for the evolution.");
                qm.dispose();
                return;
            }

            qm.sendNext("Step aside, behold the mighty prowess of Maha!!");
        } else if (status == 1) {
            qm.forceCompleteQuest();

            qm.gainItem(1902017, -1);
            qm.gainItem(1902018, 1);

            qm.dispose();
        }
    }
}
