package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
public interface MarketPlace extends Remote {

    List<Item> getItems() throws RemoteException;

    Item addItem(String name, double price, User user) throws RemoteException;

    void buyItem(Item item) throws RemoteException;
}
