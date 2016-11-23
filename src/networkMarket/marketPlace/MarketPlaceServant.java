package networkMarket.marketPlace;

import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.Store;
import networkMarket.interfaces.UserHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 11/22/16.
 */
public class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace {

    private UserHandler userHandler;
    private Store store;

    public MarketPlaceServant() throws RemoteException {

        userHandler = new UserHandlerServant();
        store = new StoreServant(userHandler);
    }

    @Override
    public UserHandler getUserHandler() throws RemoteException {
        return userHandler;
    }

    @Override
    public Store getStore() throws RemoteException {
        return store;
    }
}
