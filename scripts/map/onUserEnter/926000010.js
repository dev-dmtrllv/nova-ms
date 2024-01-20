function start(ms) {
    var map = ms.getClient().getChannelServer().getMapFactory().getMap(926000010);
    map.resetPQ(1);
    return (true);
}
