package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */
public interface User extends Remote{

    void notifySoldItem() throws RemoteException;
    Item notifyWishItem() throws RemoteException;
}
