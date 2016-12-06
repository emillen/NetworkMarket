package networkMarket.interfaces;

import networkMarket.marketPlace.Item;
import networkMarket.marketPlace.User;
import networkMarket.marketPlace.exceptions.UserException;
import se.kth.id2212.ex3.bankjpa.RejectedException;

import javax.persistence.NoResultException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daseel on 11/22/16.
 */
public interface Store extends Remote {

    List<Item> getItems(User user) throws RemoteException, UserException;

    List<Item> getItemsFromSeller (User seller) throws RemoteException, UserException, NoResultException;

    List<Item> getGetItemsFromBuyer (User buyer) throws RemoteException, UserException, NoResultException;

    Item addItem(String name, float price, User user) throws RemoteException, UserException;

    void buyItem(Item item, User user) throws RemoteException, RejectedException, UserException;
}
