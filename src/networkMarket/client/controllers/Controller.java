package networkMarket.client.controllers;

import networkMarket.interfaces.MarketPlace;

public class Controller {

    private MarketPlace market;

    public void init (MarketPlace market){
        this.market = market;
    }

    // TODO: 2016-11-14 Show items from the market
}
