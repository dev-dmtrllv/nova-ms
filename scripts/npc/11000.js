function start() {
    const ShopFactory = Java.type('server.ShopFactory');
    ShopFactory.getInstance().getShop(11000).sendShop(cm.getClient());
    cm.dispose();
}
