package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 11/22/16.
 */
public interface UserHandler extends Remote {

    User logIn(String userName, String password) throws RemoteException;

    void register(String userName, String password) throws RemoteException;

    void unregister(String userName, String password) throws RemoteException;
}
