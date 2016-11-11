package networkMarket.marketPlace.servants;

import networkMarket.marketPlace.interfaces.Item;
import networkMarket.marketPlace.interfaces.MarketPlace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
public class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace {

    HashMap<String, Item> items;

    public MarketPlaceServant() throws RemoteException {

        items = new HashMap<>();
    }

    @Override
    public List<Item> getItems() throws RemoteException {
        return null;
    }

    @Override
    public void addItem(String name, double price) throws RemoteException{


    }
}
