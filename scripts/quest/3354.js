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
            qm.sendAcceptDecline("I have a request for you. Can you ask #bMaed#k for a potion of my devise? Obviously, don't mention I have asked you that, that would be a problem. #bKeeny#k got an illness due to the contact with the Huroids, this have bothering me so much I couldn't give progress on my researches... Please #rbring her the potion#k, so that I could feel better and start making progress. I'm counting on you.");
        } else if (status == 1) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
