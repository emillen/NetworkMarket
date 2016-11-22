package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/22/16.
 */
public class LoginService extends Service<User> {

    private MarketPlace market;

    public LoginService(MarketPlace market) {

        this.market = market;
    }

    @Override
    protected Task<User> createTask() {
        return new Task<User>() {
            @Override
            protected User call() throws Exception {
                return null;
            }
        };
    }
}
