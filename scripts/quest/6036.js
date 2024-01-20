/*  Maker Skill
	A Surprise Outcome
	3rd skill level
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
            qm.sendNext("Bothering me again? What's it?");
        } else if (status == 1) {
            if (qm.haveItem(4031980, 1)) {
                qm.sendNext("You crafted a #b#t4031980##k?! How comes, how did you do it?? ... Well, that can't be helped, I guess. The student surpassed the teacher! Youth sure do wonders to one's perception capabilities.\r\n\r\nYou are now ready to take the last step on mastering the Maker skill, contemplate it at it's finest form!");
            } else {
                qm.sendNext("... Please step aside, I can't finish this work if I'm being distracted at every moment.");
                qm.dispose();

            }
        } else if (status == 2) {
            qm.forceCompleteQuest();

            qm.gainItem(4031980, -1);
            var skillid = Math.floor(qm.getPlayer().getJob().getId() / 1000) * 10000000 + 1007;
            qm.teachSkill(skillid, 3, 3, -1);
            qm.gainExp(300000);

            qm.dispose();
        }
    }
}
