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

    private HashMap<String, UserServant> users;

    public UserHandlerServant() throws RemoteException {

        users = new HashMap<>();
    }

    @Override
    public User logIn(String userName, String password) throws RemoteException {

        if (users.containsKey(userName) &&
                users.get(userName).getPassword().equals(password))
            return users.get(userName);

        return null;
    }

    @Override
    public void register(String userName, String password) throws RemoteException, UserException {
        if (users.containsKey(userName))
            throw new UserException("Username already exists");

        users.put(userName, new UserServant(userName, null, password));
    }

    @Override
    public void unregister(User user) throws RemoteException, UserException {

        if (userExists(user))
            throw new UserException("User does not exist");

        users.remove(user.getName());
    }

    @Override
    public boolean userExists(User user) throws RemoteException {
        return users.containsKey(user.getName());
    }
}
