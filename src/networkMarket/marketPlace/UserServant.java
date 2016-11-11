package networkMarket.marketPlace;

import networkMarket.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
@SuppressWarnings("serial")
class UserServant extends UnicastRemoteObject implements User {

    public UserServant() throws RemoteException{}


}
