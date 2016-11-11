package networkMarket.marketPlace.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */
public interface MarketPlace extends Remote {

    Item[] getItems () throws RemoteException;
    void addItem (String name, double price);
}
