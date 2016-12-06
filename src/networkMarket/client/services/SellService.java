package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.marketPlace.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.Store;
import networkMarket.marketPlace.User;

/**
 * Created by daseel on 11/22/16.
 */
public class SellService extends Service<Item> {

    private String name;
    private float price;
    private User user;
    private MarketPlace market;

    public SellService(String name, float price, User user, MarketPlace market) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.market = market;
    }

    @Override
    protected Task<Item> createTask() {
        return new Task<Item>() {
            @Override
            protected Item call() throws Exception {

                Store store = market.getStore();

                return store.addItem(name, price, user);
            }
        };
    }
}
