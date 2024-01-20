function enter(pi) {
    pi.playPortalSound();
    pi.warp(pi.getMapId() - 2, 0);
    return true;
}
