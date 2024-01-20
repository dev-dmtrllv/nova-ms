var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            const YamlConfig = Java.type('config.YamlConfig');
            if (YamlConfig.config.server.USE_ENABLE_CUSTOM_NPC_SCRIPT) {
                cm.openShopNPC(2082014);
            } else if (cm.isQuestStarted(3749)) {
                cm.sendOk("We've already located the enemy's ultimate weapon! Follow along the ship's bow area ahead and you will find my sister #b#p2082013##k. Report to her for futher instructions on the mission.");
            } else {
                cm.sendDefault();
            }

            cm.dispose();
        }
    }
}
