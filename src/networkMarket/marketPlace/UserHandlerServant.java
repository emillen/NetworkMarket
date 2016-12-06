package networkMarket.marketPlace;

import networkMarket.interfaces.UserHandler;
import networkMarket.marketPlace.exceptions.UserException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

/**
 * Created by daseel on 11/22/16.
 */
public class UserHandlerServant extends UnicastRemoteObject implements UserHandler {

    private EntityManagerFactory emf;

    public UserHandlerServant() throws RemoteException {
        emf = Persistence.createEntityManagerFactory("NetworkMarket");
    }

    @Override
    public User logIn(String username, String password) throws RemoteException, UserException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = em.createNamedQuery("findUserWithName", User.class)
                .setParameter("name", username).getSingleResult();
        if (user == null || !user.getPassword().equals(password))
            throw new UserException("Username and password does not match");

        return user;
    }

    @Override
    public void register(String username, String password) throws RemoteException, UserException {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            List<User> existingUsers = em.createNamedQuery("findUserWithName", User.class)
                    .setParameter("name", username).getResultList();
            if (existingUsers.size() > 0)
                throw new UserException("Username already exists");

            User newUser = new User(username, password);
            em.persist(newUser);
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void unregister(User user) throws RemoteException, UserException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createNamedQuery("deleteUserWithName", User.class).setParameter("name", user.getName());
    }

    @Override
    public boolean userLoggedIn(User user) throws RemoteException {
        EntityManager em = emf.createEntityManager();
        User storedUser = em.createNamedQuery("findUserWithName", User.class)
                .setParameter("name", "blu").getSingleResult();

        return (storedUser != null && storedUser.getPassword().equals(user.getPassword()));
    }
}
