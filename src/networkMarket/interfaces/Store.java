package networkMarket.interfaces;

import networkMarket.marketPlace.Item;
import networkMarket.marketPlace.User;
import networkMarket.marketPlace.exceptions.UserException;
import se.kth.id2212.ex3.bankjpa.RejectedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by daseel on 11/22/16.
 */
public interface Store extends Remote {

    List<Item> getItems(User user) throws RemoteException, UserException;

    List<Item> getItemsFromSeller (User seller) throws RemoteException, UserException;

    List<Item> getGetItemsFromBuyer (User buyer) throws RemoteException, UserException;

    Item addItem(String name, float price, User user) throws RemoteException, UserException;

    void buyItem(Item item, User user) throws RemoteException, RejectedException, UserException;

    void wishItem(String name, float price, User user) throws RemoteException, UserException;

    List<Wish> getMyWishes(User user) throws RemoteException, UserException;

    void removeFromWishList(Wish wish, User user) throws RemoteException;
}
