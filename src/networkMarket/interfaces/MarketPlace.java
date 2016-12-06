package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */
public interface MarketPlace extends Remote {

    UserHandler getUserHandler() throws RemoteException;

    Store getStore() throws RemoteException;
}
