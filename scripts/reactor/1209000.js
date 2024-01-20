/* @Author Ronan
 * 
 * 1200000.js: Bart reactor, Aerial Strike skill
*/

function act() {    // string visibility thanks to ProXAIMeRx & Glvelturall
    if (rm.isQuestStarted(6400)) {
        rm.setQuestProgress(6400, 1, 2);
        rm.setQuestProgress(6400, 6401, "q3");
    }

    rm.message("Real Bart has been found. Return to Jonathan through the portal.");
}
