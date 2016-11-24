package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;
import networkMarket.interfaces.Wish;

import java.util.List;

/**
 * Created by daseel on 11/24/16.
 */
public class CheckWishesService extends Service<Wish> {

    private User user;
    private MarketPlace market;


    public CheckWishesService(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }

    @Override
    protected Task<Wish> createTask() {


        return new Task<Wish>() {
            @Override
            protected Wish call() throws Exception {
                Thread.sleep(1000);
                List<Wish> myWishList = market.getStore().getMyWishes(user);
                Wish foundWish = null;
                for (Wish w : myWishList) {
                    if (w.beenFound()) {
                        foundWish = w;
                        myWishList.remove(w);
                    }
                }
                return foundWish;
            }
        };
    }
}
