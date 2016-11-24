package networkMarket.interfaces;

import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 11/22/16.
 */
public interface UserHandler extends Remote {

    void logIn(User user) throws RemoteException, UserException;

    void register(User newUser) throws RemoteException, UserException;

    void unregister(User user) throws RemoteException, UserException;
}
