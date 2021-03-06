package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.UserHandler;

/**
 * Created by daseel on 11/22/16.
 */
public class RegisterService extends Service<Void> {

    private MarketPlace market;
    private String username;
    private String password;


    public RegisterService(MarketPlace market, String username, String password) {
        this.market = market;
        this.username = username;
        this.password = password;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                UserHandler handler = market.getUserHandler();


                handler.register(username, password);

                return null;
            }
        };
    }
}
