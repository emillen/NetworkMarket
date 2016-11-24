package networkMarket.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by daseel on 11/24/16.
 */
public interface Wish extends Remote{

    String getItemName() throws RemoteException;
    double getPrice() throws RemoteException;
    boolean beenFound() throws RemoteException;
    void setBeenFound(boolean beenFound) throws RemoteException;
}
