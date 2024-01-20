/* 
	Quest: Hughes the Fuse's Basic of Theory of Science
 */

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
            qm.sendNext("I am to teach you about the basics of the Theory of Science.");
        } else if (status == 1) {
            qm.sendNextPrev("Science stages where the alchemy doesn't meet the requirements. All items have molecular constitutions. The #rnature of their arrangements and each intrinsic unit of matter#k defines the many properties an item will have.");
        } else if (status == 2) {
            qm.sendNextPrev("This makes true in the scenario of the #rMaker#k as well. One must be able to study the traces of each component that is being used to form the item, to be able to tell if the experiment will utmostly succeed of fail.");
        } else if (status == 3) {
            qm.sendNextPrev("Take that in mind: the main perspective of science, that one engine that makes it flows the strongest, whatever scenario it is, is the aspect of #bunderstanding the process#k that generates the results, not simply throwing away tries at will.");
        } else if (status == 4) {
            qm.sendNextPrev("That has been made clear, right? Good, then the class is over. Dismissed.");
        } else if (status == 5) {
            qm.gainMeso(-10000);

            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}
