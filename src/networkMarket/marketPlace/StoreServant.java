package networkMarket.marketPlace;

import networkMarket.interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class StoreServant extends UnicastRemoteObject implements Store {

    private List<Item> items;
    private UserHandler userHandler;

    StoreServant(UserHandler userHandler) throws RemoteException {

        items = new ArrayList<>();
        this.userHandler = userHandler;

    }

    @Override
    public synchronized List<Item> getItems() throws RemoteException {
        return items;
    }

    @Override
    public synchronized Item addItem(String name, double price, User seller) throws RemoteException {
        Item item = new ItemServant(name, price, seller);
        items.add(item);
        return item;
    }

    @Override
    public synchronized void buyItem(Item item) throws RemoteException {
        // TODO: 2016-11-11 Check if user can actually buy it

        for (Item i : items) {
            if (i.getName().equals(item.getName())
                    && i.getSeller().getName().equals(item.getSeller().getName())) {
                items.remove(i);
                item.getSeller().notifySoldItem();
                break;
            }

        }

    }
}
