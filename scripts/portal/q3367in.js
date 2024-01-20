function enter(pi) {
    if (pi.isQuestStarted(3367)) {
        var booksDone = pi.getQuestProgressInt(3367, 31);
        var booksInv = pi.getItemQuantity(4031797);

        if (booksInv < booksDone) {
            pi.gainItem(4031797, booksDone - booksInv);
        }

        pi.playPortalSound();
        pi.warp(926130102, 0);
        return true;
    } else {
        pi.message("You don't have permission to access this room.");
        return false;
    }

}
