/**
 * @author: Ronan
 * @npc: Investigation Result
 * @func: Gives MagatiaPQ stg1 item
 */

function start() {
    var eim = cm.getEventInstance();
    var book = "stg1_b" + (cm.getNpcObjectId() % 26);

    var res = eim.getIntProperty(book);
    if (res > -1) {
        eim.setIntProperty(book, -1);

        if (res == 0) {  // mesos
            var mgain = 500 * cm.getPlayer().getMesoRate();
            cm.sendNext("Earned " + mgain + " mesos!");
            cm.gainMeso(mgain);
        } else if (res == 1) {  // exp
            var egain = 500 * cm.getPlayer().getExpRate();
            cm.sendNext("Earned " + egain + " exp!");
            cm.gainExp(egain);
        } else if (res == 2) {  // letter
            var letter = 4001131;
            if (!cm.canHold(letter)) {
                cm.sendOk("You got a letter, however it didn't fit on your inventory, so you put it back.");
                cm.dispose();
                return;
            }

            cm.gainItem(letter, 1);
            cm.sendNext("You found a letter, strategically placed here as it seems.");
        } else if (res == 3) {  // pass
            cm.sendNext("You found the trigger to the next stage.");

            var eim = cm.getEventInstance();
            eim.showClearEffect();
            eim.giveEventPlayersStageReward(1);
            eim.setIntProperty("statusStg1", 1);

            cm.getMap().getReactorByName("d00").hitReactor(cm.getClient());
        }
    } else {
        cm.sendNext("There is nothing here.");
    }

    cm.dispose();
}
