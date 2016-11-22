package networkMarket.marketPlace;

import networkMarket.interfaces.User;
import networkMarket.interfaces.UserHandler;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Created by daseel on 11/22/16.
 */
public class UserHandlerServant implements UserHandler {

    HashMap<String, UserServant> users;

    public UserHandlerServant() {

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
    public void register(String userName, String password) throws RemoteException {

    }

    @Override
    public void unregister(String userName, String password) throws RemoteException {

    }
}
