package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.User;

/**
 * Created by daseel on 11/24/16.
 */
public class UnregisterService extends Service<Void>{

    private MarketPlace marketPlace;
    private User user;


    public UnregisterService(User user, MarketPlace marketPlace){

        this.user = user;
        this.marketPlace = marketPlace;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                marketPlace.getUserHandler().unregister(user);
                return null;
            }
        };
    }
}
