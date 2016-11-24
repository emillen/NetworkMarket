package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;
import networkMarket.interfaces.UserHandler;
import networkMarket.marketPlace.UserServant;

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

                User user = new UserServant(username, password);
                UserHandler handler = market.getUserHandler();
                handler.logIn(user);
                return user;
            }
        };
    }
}
