package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.client.controllers.MakeWishController;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/24/16.
 */
public class MakeWishService extends Service<Void> {


    private User user;
    private MarketPlace market;
    private String name;
    private double price;

    public MakeWishService(String name, double price, User user, MarketPlace marketPlace) {
        this.user = user;
        this.market = marketPlace;
        this.name = name;
        this.price = price;

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                System.out.println(user.getName());
                market.getStore().wishItem(name, price, user);
                return null;
            }
        };
    }
}
