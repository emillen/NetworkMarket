package networkMarket.marketPlace;

import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace {

    private List<Item> items;


    MarketPlaceServant() throws RemoteException {

        items = new ArrayList<>();
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
