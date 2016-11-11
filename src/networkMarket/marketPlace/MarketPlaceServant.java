package networkMarket.marketPlace;

import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace {

    List<Item> items;

    MarketPlaceServant() throws RemoteException {

        items = new ArrayList<>();
    }

    @Override
    public List<Item> getItems() throws RemoteException {
        return items;
    }

    @Override
    public void addItem(String name, double price) throws RemoteException{


        items.add(new ItemServant(name, price));
    }
}
