package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.marketPlace.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.User;

import javax.persistence.NoResultException;
import java.util.ArrayList;
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

                try {
                    return market.getStore().getItems(user);
                } catch (NoResultException e) {
                    return new ArrayList<>();
                }
            }
        };
    }
}
