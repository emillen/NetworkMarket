package networkMarket.marketPlace;

import networkMarket.interfaces.Wish;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 11/24/16.
 */
public class WishServant extends UnicastRemoteObject implements Wish {

    private String name;
    private double price;
    private boolean hasBeenFound;

    WishServant(String name, double price) throws RemoteException {
        this.name = name;
        this.price = price;
        this.hasBeenFound = false;
    }

    @Override
    public String getItemName() throws RemoteException {
        return name;
    }

    @Override
    public double getPrice() throws RemoteException {
        return price;
    }

    @Override
    public boolean beenFound() throws RemoteException {
        return hasBeenFound;
    }

    @Override
    public void setBeenFound(boolean beenFound) throws RemoteException {
        hasBeenFound = beenFound;
    }
}
