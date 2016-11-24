package networkMarket.marketPlace;

import networkMarket.interfaces.User;
import networkMarket.interfaces.UserHandler;
import networkMarket.marketPlace.exceptions.UserException;

import javax.jws.soap.SOAPBinding;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Created by daseel on 11/22/16.
 */
public class UserHandlerServant extends UnicastRemoteObject implements UserHandler {

    private HashMap<String, User> users;

    public UserHandlerServant() throws RemoteException {

        users = new HashMap<>();
    }

    @Override
    public void logIn(User user) throws RemoteException, UserException {

        if (userExists(user) &&
                users.get(user.getName()).getPassword().equals(user.getPassword()))
            return;

        throw new UserException("Username and password does not match");
    }

    @Override
    public void register(User newUser) throws RemoteException, UserException {
        if (userExists(newUser))
            throw new UserException("Username already exists");

        users.put(newUser.getName(), newUser);
    }

    @Override
    public void unregister(User user) throws RemoteException, UserException {

        if (userExists(user))
            throw new UserException("User does not exist");

        users.remove(user.getName());
    }


    private boolean userExists(User user) throws RemoteException {
        return users.containsKey(user.getName());
    }
}
