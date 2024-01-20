/* 
	Ardin - Sand Bandits team challenge
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
            qm.sendNext("I didn't think you would be this strong. I feel like you have what it takes to become a member of the Sand Bandits. The most important aspect of being a member is power, and I think you have that. I also... want to test you one more time, just to make sure you're the right one. What do you think? Can you handle it?");
        } else if (status == 1) {
            qm.sendAcceptDecline("To truly see your strength, I'll have to face you myself. Don't worry, I'll summon my other self to face off against you. Are you ready?");
        } else if (status == 2) {
            qm.sendNext("Good, I like your confidence.");
        } else if (status == 3) {
            if (qm.getWarpMap(926000000).getCharacters().size() > 0) {
                qm.sendOk("There is someone currently in this map, come back later.");
                qm.dispose();
            } else {
                qm.warp(926000000, "st00");
                qm.forceStartQuest();
                qm.dispose();
            }
        }
    }
}
