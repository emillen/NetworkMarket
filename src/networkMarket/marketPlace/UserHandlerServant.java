package networkMarket.marketPlace;

import networkMarket.interfaces.User;
import networkMarket.interfaces.UserHandler;
import networkMarket.marketPlace.exceptions.UserException;

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
    public User logIn(String username, String password) throws RemoteException, UserException {

        if (userExists(username) &&
                users.get(username).getPassword().equals(password)) {

            return users.get(username);
        }

        throw new UserException("Username and password does not match");
    }

    @Override
    public void register(String username, String password) throws RemoteException, UserException {
        if (userExists(username))
            throw new UserException("Username already exists");

        User newUser = new UserServant(username, password);
        users.put(username, newUser);
    }

    @Override
    public void unregister(User user) throws RemoteException, UserException {

        if (!userExists(user.getName()) && !user.getPassword().equals(users.get(user.getName()).getPassword()))
            throw new UserException("User does not exist");

        users.remove(user.getName());
    }


    @Override
    public boolean userExists(String username) throws RemoteException {
        return users.containsKey(username);
    }
}
