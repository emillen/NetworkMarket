package networkMarket.marketPlace;

import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.UserHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 11/22/16.
 */
public class MarketPlaceServant extends UnicastRemoteObject implements MarketPlace {

    private UserHandler userHandler;

    public MarketPlaceServant() throws RemoteException {

        userHandler = new UserHandlerServant();
    }

    @Override
    public UserHandler getUserHandler() throws RemoteException {
        return userHandler;
    }
}
