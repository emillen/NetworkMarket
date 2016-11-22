package networkMarket.marketPlace;

import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.UserHandler;

import java.rmi.RemoteException;

/**
 * Created by daseel on 11/22/16.
 */
public class MarketPlaceServant implements MarketPlace {

    private UserHandler userHandler;

    public MarketPlaceServant() {

        userHandler = new UserHandlerServant();
    }

    @Override
    public UserHandler getUserHandler() throws RemoteException {
        return userHandler;
    }
}
