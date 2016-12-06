package networkMarket.client.controllers;

import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.User;

/**
 * Created by daseel on 11/22/16.
 */
public interface Controller {
    void init(User user, MarketPlace market);
}
