package networkMarket.marketPlace;

import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.Store;
import networkMarket.interfaces.UserHandler;
import networkMarket.interfaces.Wish;
import networkMarket.marketPlace.exceptions.UserException;
import se.kth.id2212.ex3.bankjpa.Bank;
import se.kth.id2212.ex3.bankjpa.RejectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */
class StoreServant extends UnicastRemoteObject implements Store {


    private UserHandler userHandler;
    private EntityManagerFactory emf;
    private Bank bank;


    StoreServant(UserHandler userHandler) throws RemoteException {
        try {
            try {
                LocateRegistry.getRegistry(1099).list();

            } catch (RemoteException e) {
                LocateRegistry.createRegistry(1099);
                System.out.println(e.getMessage());
            }
            this.bank = (Bank) Naming.lookup("Nordea");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.emf = Persistence.createEntityManagerFactory("NetworkMarket");
        this.userHandler = userHandler;
    }

    @Override
    public synchronized List<Item> getItems(User viewer) throws RemoteException, UserException, NoResultException {
        checkUser(viewer);

        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("findAllItems", Item.class).getResultList();
    }

    @Override
    public synchronized Item addItem(String name, float price, User seller) throws RemoteException, UserException {
        checkUser(seller);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Item item = new Item(name, price, seller);

        em.persist(item);

        em.getTransaction().commit();

        return item;
    }


    @Override
    public synchronized void buyItem(Item item, User buyer) throws RemoteException, RejectedException, UserException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        checkUser(buyer);

        bank.withdraw(buyer.getName(), item.getPrice());
        bank.deposit(item.getSeller().getName(), item.getPrice());
        item.setbuyer(buyer);

        em.getTransaction().commit();
    }

    private void

    @Override
    public void wishItem(String name, float price, User user) throws RemoteException, UserException {
    }

    @Override
    public List<Wish> getMyWishes(User user) throws RemoteException {

        return null;
    }

    private void checkWishes(Item item) throws RemoteException {

    }

    @Override
    public void removeFromWishList(Wish wish, User user) throws RemoteException {
    }

    private void checkUser(User user) throws RemoteException, UserException {
        if (!userHandler.userLoggedIn(user))
            throw new UserException("Not logged in");
    }
}
