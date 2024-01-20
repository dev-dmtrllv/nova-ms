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
            qm.sendNext("Okay, you should not return to #bTru#k for further details on your next steps. ... Oh wait!! I remembered something. See the #rMysterious Statue#k over there? That statue has it's origins unknwown, and there's something scribbled onto it that resembles something big, it probably is the password for the cave? #rGet the password there#k, it may help you on your journey.");
        } else if (status == 1) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
