/* 
	Map(s): 		Empress' Road : Training Forest II
	Description: 		Takes you to Timu's Forest
*/

var jobtype = 3;

function enter(pi) {
    if (pi.isQuestStarted(20301) || pi.isQuestStarted(20302) || pi.isQuestStarted(20303) || pi.isQuestStarted(20304) || pi.isQuestStarted(20305)) {
        var map = pi.getClient().getChannelServer().getMapFactory().getMap(108010600 + (10 * jobtype));
        if (map.countPlayers() > 0) {
            pi.message("Someone else is already searching the area.");
            return false;
        }

        if (pi.haveItem(4032101 + jobtype, 1)) {
            pi.message("You have already challenged the Master of Disguise, report your success to the Chief Knight.");
            return false;
        }

        pi.playPortalSound();
        pi.warp(108010600 + (10 * jobtype), "out00");
    } else {
        pi.playPortalSound();
        pi.warp(130010120, "out00");
    }
    return true;
}
