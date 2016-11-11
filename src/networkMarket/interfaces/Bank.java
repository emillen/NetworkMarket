package networkMarket.interfaces;

import networkMarket.bank.exceptions.RejectedException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
    Account newAccount(String name) throws RemoteException, RejectedException;

    Account getAccount(String name) throws RemoteException;

    boolean deleteAccount(String name) throws RemoteException;

    String[] listAccounts() throws RemoteException;
}
