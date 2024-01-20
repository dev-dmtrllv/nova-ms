function isTigunMorphed(ch) {
    const BuffStat = Java.type('client.BuffStat');
    return ch.getBuffSource(BuffStat.MORPH) == 2210005;
}

function enter(pi) {
    if (isTigunMorphed(pi.getPlayer())) {
        return false;
    } else {
        pi.playPortalSound();
        pi.warp(260000300, 7);
        pi.message("You, intruder! You don't have permission to roam the palace! Get out!!");
        return true;
    }
}
