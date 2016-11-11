package networkMarket.marketPlace.servants;

import networkMarket.marketPlace.interfaces.Item;
import networkMarket.marketPlace.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
public class ItemServant extends UnicastRemoteObject implements Item {

    private String name;
    private double price;

    public ItemServant(String name, double price) throws RemoteException {
        this.name = name;
        this.price = price;
    }

    @Override
    public synchronized double getPrice() throws RemoteException {
        return price;
    }

    @Override
    public synchronized String getName() throws RemoteException {
        return name;
    }

    @Override
    public User seller() throws RemoteException {
        return null;
    }
}
