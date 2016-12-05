package networkMarket.marketPlace;

import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.Store;
import networkMarket.interfaces.UserHandler;
import networkMarket.interfaces.Wish;
import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class StoreServant extends UnicastRemoteObject implements Store {


    private UserHandler userHandler;

    StoreServant(UserHandler userHandler) throws RemoteException {
        this.userHandler = userHandler;
    }

    @Override
    public synchronized List<Item> getItems(User viewer) throws RemoteException, UserException {
        return null;
    }

    @Override
    public synchronized Item addItem(String name, double price, User seller) throws RemoteException, UserException {

        return null;
    }


    @Override
    public synchronized void buyItem(Item item, User buyer) throws RemoteException, RejectedException, UserException {

    }

    @Override
    public void wishItem(String name, double price, User user) throws RemoteException, UserException {
    }

    @Override
    public List<Wish> getMyWishes(User user) throws RemoteException {

        return null;
    }

    private void checkWishes(Item item) throws RemoteException {

    }

    @Override
    public void removeFromWishList(Wish wish, User user) throws RemoteException {
    }

    private void checkUser(User user) throws RemoteException, UserException {

        if (!userHandler.userExists(user.getName()))
            throw new UserException("You are not logged in dawg");
    }
}
