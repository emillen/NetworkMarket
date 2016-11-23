package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.interfaces.Item;

import java.util.List;

/**
 * Created by daseel on 11/23/16.
 */
public class GetItemsService extends Service<List<Item>>{

    public GetItemsService(){

    }

    @Override
    protected Task<List<Item>> createTask() {
        return null;
    }
}
