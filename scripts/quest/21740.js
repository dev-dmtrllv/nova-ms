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
            qm.sendNext("The Orbis seal has been stolen by the Black Wings? Hmm, that has gone awry. Go tell #bLilin#k about this, she must have something in mind on this situation.");
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
            qm.sendNext("Oh, hi #h0#! You won't believe what I just uncovered. It's one of your lost skills... What, the seal of Orbis got stolen by the Black Wings? Oh my...");
        } else if (status == 1) {
            qm.sendNext("For now, let me teach you the #bCombo Smash#k, with it you will be able to deal massive amount of damage to many monsters at once. We will need to use it if we want to stand a chance against the Black Wings now, so don't forget it!");
        } else if (status == 2) {
            qm.forceCompleteQuest();
            qm.teachSkill(21100004, 0, 20, -1); // combo smash
            qm.dispose();
        }
    }
}
