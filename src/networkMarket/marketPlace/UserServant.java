package networkMarket.marketPlace;

import networkMarket.interfaces.Account;
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


    public UserServant(String name, String password) throws RemoteException {
        this.name = name;
        this.password = password;
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

    @Override
    public void setBankAccount(Account acc) throws RemoteException {
        bankAccount = acc;
    }
}
