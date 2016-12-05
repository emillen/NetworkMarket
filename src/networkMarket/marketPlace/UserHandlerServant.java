package networkMarket.marketPlace;

import networkMarket.interfaces.UserHandler;
import networkMarket.marketPlace.exceptions.UserException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Created by daseel on 11/22/16.
 */
public class UserHandlerServant extends UnicastRemoteObject implements UserHandler {


    public UserHandlerServant() throws RemoteException {

    }

    @Override
    public User logIn(String username, String password) throws RemoteException, UserException {


        throw new UserException("Username and password does not match");
    }

    @Override
    public void register(String username, String password) throws RemoteException, UserException {
        if (userExists(username))
            throw new UserException("Username already exists");

    }

    @Override
    public void unregister(User user) throws RemoteException, UserException {

    }


    @Override
    public boolean userExists(String username) throws RemoteException {
        return false;
    }
}
