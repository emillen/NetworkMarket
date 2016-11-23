package networkMarket.marketPlace;

import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.*;
import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class StoreServant extends UnicastRemoteObject implements Store {

    private List<Item> items;
    private List<Item> wishList;
    private UserHandler userHandler;

    StoreServant(UserHandler userHandler) throws RemoteException {

        items = new ArrayList<>();
        wishList = new ArrayList<>();
        this.userHandler = userHandler;

    }

    @Override
    public synchronized List<Item> getItems(User viewer) throws RemoteException, UserException {
        checkUser(viewer);
        return items;
    }

    @Override
    public synchronized Item addItem(String name, double price, User seller) throws RemoteException, UserException {

        checkUser(seller);
        Item item = new ItemServant(name, price, seller);
        //notifyWishList(item);
        items.add(item);
        return item;
    }

    private void notifyWishList(Item item) throws RemoteException{

        for(Item wishItem : wishList){
            if(wishItem.getName().equals(item.getName()) && wishItem.getPrice() <= item.getPrice())
                wishItem.getSeller().notifyWishItem(item);
        }
    }

    @Override
    public synchronized void buyItem(Item item, User buyer) throws RemoteException, RejectedException, UserException {

        checkUser(buyer);

        if (buyer.getBankAccount() == null)
            throw new RejectedException("No bank account, dude");

        for (Item i : items) {
            if (i.getName().equals(item.getName())
                    && i.getSeller().getName().equals(item.getSeller().getName())) {
                buyer.getBankAccount().withdraw((float) item.getPrice());
                item.getSeller().getBankAccount().deposit((float) item.getPrice());
                items.remove(i);
                item.getSeller().notifySoldItem();
                break;
            }

        }
    }

    private void checkUser(User user) throws UserException, RemoteException {

        if (user == null || !userHandler.userExists(user))
            throw new UserException("Account does not exist");
    }
}
