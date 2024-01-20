/* 
 * @Author Ronan
 * Player NPC Ranking System */

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
            var pnpc = cm.getPlayerNPCByScriptid(cm.getNpc());

            if (pnpc != null) {
                const GameConstants = Java.type('constants.game.GameConstants');
                var branchJobName = GameConstants.getJobName(pnpc.getJob());

                var rankStr = "Hi, I am #b" + pnpc.getName() + "#k, #r" + GameConstants.ordinal(pnpc.getWorldJobRank()) + "#k in the #r" + branchJobName + "#k class to reach the max level and obtain a statue on " + GameConstants.WORLD_NAMES[cm.getPlayer().getWorld()] + ".\r\n";
                rankStr += "\r\n    World rank: #e#b" + GameConstants.ordinal(pnpc.getWorldRank()) + "#k#n";
                rankStr += "\r\n    Overall " + branchJobName + " rank: #e#b" + GameConstants.ordinal(pnpc.getOverallJobRank()) + "#k#n";
                rankStr += "\r\n    Overall rank: #e#b" + GameConstants.ordinal(pnpc.getOverallRank()) + "#k#n";

                cm.sendOk(rankStr);
            } else {
                cm.sendOk("Hi, how're you doing?");
            }

            cm.dispose();
        }
    }
}
