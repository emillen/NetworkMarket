package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daseel on 11/22/16.
 */
public interface Store extends Remote {

    List<Item> getItems(User user) throws RemoteException;

    Item addItem(String name, double price, User user) throws RemoteException;

    void buyItem(Item item, User user) throws RemoteException;
}
