package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */
public interface User extends Remote {

    Account getBankAccount() throws RemoteException;

    void setBankAccount(Account acc) throws RemoteException;

    String getName() throws RemoteException;

    String getPassword() throws RemoteException;
}
