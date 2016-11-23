package networkMarket.interfaces;

import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 11/22/16.
 */
public interface UserHandler extends Remote {

    User logIn(String userName, String password) throws RemoteException, UserException;

    void register(String userName, String password) throws RemoteException, UserException;

    void unregister(User user) throws RemoteException, UserException;

    boolean userExists(User user) throws RemoteException;
}
