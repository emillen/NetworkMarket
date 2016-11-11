package networkMarket.marketPlace.servants;

import networkMarket.marketPlace.interfaces.Item;
import networkMarket.marketPlace.interfaces.MarketPlace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
public class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace{
    public MarketPlaceServant() throws RemoteException{

    }

    @Override
    public Item[] getItems() throws RemoteException {
        return new Item[0];
    }

    @Override
    public void addItem(String name, double price) {

    }
}
