package networkMarket.marketPlace;

import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.*;
import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class StoreServant extends UnicastRemoteObject implements Store {

    private List<Item> items;
    private UserHandler userHandler;
    private HashMap<String, List<Wish>> wishlist;

    StoreServant(UserHandler userHandler) throws RemoteException {

        this.items = new ArrayList<>();
        this.userHandler = userHandler;
        this.wishlist = new HashMap<>();
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
        checkWishes(item);
        items.add(item);
        return item;
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
                item.setIsSold(true);
                break;
            }

        }
    }

    @Override
    public void wishItem(String name, double price, User user) throws RemoteException, UserException {
        checkUser(user);

        Wish wish = new WishServant(name, price);
        if (wishlist.containsKey(user.getName())) {
            wishlist.get(user.getName()).add(wish);
        } else {
            List<Wish> userList = new ArrayList<>();
            userList.add(wish);
            wishlist.put(user.getName(), userList);

        }
    }

    @Override
    public List<Wish> getMyWishes(User user) throws RemoteException {
        if (wishlist.containsKey(user.getName()))
            return wishlist.get(user.getName());

        return null;
    }

    private void checkWishes(Item item) throws RemoteException {

        List<List<Wish>> allWishes = new ArrayList<>(wishlist.values());

        for (List<Wish> l : allWishes) {
            for (Wish w : l) {

                if (w.getPrice() >= item.getPrice() && w.getItemName().equals(w.getItemName())) {
                    w.setBeenFound(true);
                }
            }
        }
    }

    @Override
    public void removeFromWishList(Wish wish, User user) throws RemoteException {
        List<Wish> userWishes = wishlist.get(user.getName());

        for(int i = 0; i < userWishes.size(); i++){
            if(userWishes.get(i).getItemName().equals(wish.getItemName()) && userWishes.get(i).getPrice() == wish.getPrice())
                userWishes.remove(i);
        }

    }

    private void checkUser(User user) throws RemoteException, UserException {

        if (!userHandler.userExists(user.getName()))
            throw new UserException("You are not logged in dawg");
    }
}
