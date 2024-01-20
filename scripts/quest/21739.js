var status = -1;

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
            qm.sendNext("So, have you defeated the giant? Oh, a Black Wing agent undercover? And he GOT THE SEAL STONE OF ORBIS?! Oh, no. That's horrible! We need to develop countermeasures as soon as possible! Tell the informant on Lith about the situation.");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.gainExp(29500);
            qm.dispose();
        }
    }
}
