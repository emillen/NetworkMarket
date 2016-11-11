package networkMarket.marketPlace;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
@SuppressWarnings("serial")
public class UserImpl extends UnicastRemoteObject implements User{

    public UserImpl() throws RemoteException{}


}
