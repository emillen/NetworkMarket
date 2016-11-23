package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/23/16.
 */
public class BuyService extends Service<Void> {

    private Item item;
    private User user;
    private MarketPlace market;

    public BuyService(Item item, User user, MarketPlace market) {
        this.item = item;
        this.user = user;
        this.market = market;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                market.getStore().buyItem(item, user);
                return null;
            }
        };
    }
}
