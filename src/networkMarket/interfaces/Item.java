package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */
public interface Item extends Remote {

    double getPrice() throws RemoteException;

    String getName() throws RemoteException;

    User seller() throws RemoteException;
}