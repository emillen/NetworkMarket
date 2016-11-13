package networkMarket.client;

import networkMarket.interfaces.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by daseel on 2016-11-11.
 */
@SuppressWarnings("serial")
class ClientServant extends UnicastRemoteObject implements User {

    public ClientServant() throws RemoteException{}


}
