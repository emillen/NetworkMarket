package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.util.List;

/**
 * Created by daseel on 11/23/16.
 */
public class GetItemsService extends Service<List<Item>> {

    private User user;
    private MarketPlace market;

    public GetItemsService(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }

    @Override
    protected Task<List<Item>> createTask() {
        return new Task<List<Item>>() {
            @Override
            protected List<Item> call() throws Exception {

                return market.getStore().getItems(user);
            }
        };
    }
}
