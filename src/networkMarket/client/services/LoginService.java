package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.User;
import networkMarket.interfaces.UserHandler;

/**
 * Created by daseel on 11/22/16.
 */
public class LoginService extends Service<User> {

    private MarketPlace market;
    private String username;
    private String password;

    public LoginService(MarketPlace market, String username, String password) {

        this.market = market;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Task<User> createTask() {
        return new Task<User>() {
            @Override
            protected User call() throws Exception {
                UserHandler handler = market.getUserHandler();

                return handler.logIn(username, password);
            }
        };
    }
}
