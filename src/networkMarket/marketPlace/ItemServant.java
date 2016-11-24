package networkMarket.marketPlace;

import networkMarket.interfaces.Item;
import networkMarket.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
class ItemServant extends UnicastRemoteObject implements Item {

    private String name;
    private double price;
    private User seller;
    private boolean isSold;

    ItemServant(String name, double price, User seller) throws RemoteException {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.isSold = false;
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
    public synchronized User getSeller() throws RemoteException {
        return seller;
    }

    @Override
    public void setIsSold(boolean isSold) throws RemoteException {
        this.isSold = isSold;
    }

    @Override
    public boolean isSold() throws RemoteException {
        return isSold;
    }
}
