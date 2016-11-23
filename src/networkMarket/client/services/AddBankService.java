package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/23/16.
 */
public class AddBankService extends Service<Void> {

    private User user;
    private String accountName;

    public AddBankService(User user, String accountName) {

        this.user = user;
        this.accountName = accountName;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                return null;
            }
        };
    }
}
