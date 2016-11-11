package networkMarket.marketPlace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
public class ItemImpl extends UnicastRemoteObject implements Item{



    public ItemImpl() throws RemoteException{
        
    }

    @Override
    public synchronized int getPrice() throws RemoteException {
        return 0;
    }

    @Override
    public synchronized int getName() throws RemoteException {
        return 0;
    }

    @Override
    public User seller() throws RemoteException {
        return null;
    }
}
