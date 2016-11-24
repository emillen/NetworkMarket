package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.Item;

/**
 * Created by daseel on 11/24/16.
 */
public class CheckSoldService extends Service<Void> {

    private Item item;

    public CheckSoldService(Item item){
        this.item = item;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while(!item.isSold())
                    Thread.sleep(1000);
                return null;
            }
        };
    }
}
