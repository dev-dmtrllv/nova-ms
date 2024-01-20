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
            qm.sendNext("Aran, I've discovered some disturbing news... You said you've come from the eastern forest section, right? We traced and studied the magic being used to support the portal over there. It turns out that's of a #rtemporal#k-type. The garments you're using... They were never seen around before. That must mean, #ryou must have come from the future#k.");
        } else if (status == 1) {
            qm.sendNext("Now about the problem: the Seal Stone that seems to have been missing in your timeline... It is a powerful artifact, that prevents the army of the #rBlack Mage#k from laying siege on our world. If that stone goes away, nothing more can prevent him. As this is a matter of great importance, find the #rself of mine#k from the future. I'm actually a #rfairy#k with a great life expectancy, I must be alive even on your timeline. Got it, #rfetch the me from the future#k!");
        } else if (status == 2) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
