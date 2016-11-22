package networkMarket.marketPlace;

import networkMarket.interfaces.Account;
import networkMarket.interfaces.Item;
import networkMarket.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */

@SuppressWarnings("serial")
public class UserServant extends UnicastRemoteObject implements User {

    private String name;
    private Account bankAccount;
    private String password;

    public UserServant(String name, Account bankAccount, String password) throws RemoteException {
        this.name = name;
        this.bankAccount = bankAccount;
        this.password = password;
    }

    @Override
    public void notifySoldItem() throws RemoteException {
        System.out.println("I sold an item");
    }

    @Override
    public Item notifyWishItem(Item item) throws RemoteException {
        System.out.println("My item exists! Woohoo. Stuff is going well for me " + item.getName());
        return item;
    }

    @Override
    public Account getBankAccount() throws RemoteException {
        return bankAccount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() throws RemoteException {
        return password;
    }
}