package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/22/16.
 */
public class SellService extends Service<Void> {

    private String name;
    private double price;
    private User user;
    private MarketPlace market;

    public SellService(String name, double price, User user, MarketPlace market) {
        this.name = name;
        this.price = price;
        this.user = user;
        this.market = market;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                // TODO: 11/22/16 Write a way to interact with the store
                return null;
            }
        };
    }
}
