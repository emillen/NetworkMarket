package networkMarket.marketPlace;

import networkMarket.interfaces.Store;
import networkMarket.interfaces.UserHandler;
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
            LocateRegistry.getRegistry(1099).list();
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
    public List<Item> getItemsFromSeller(User seller) throws RemoteException, UserException, NoResultException {
        checkUser(seller);

        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("findItemsFromSeller", Item.class)
                .setParameter("sellerName", seller.getName()).getResultList();
    }

    @Override
    public List<Item> getGetItemsFromBuyer(User buyer) throws RemoteException, UserException, NoResultException {
        checkUser(buyer);

        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("findItemsFromBuyer", Item.class)
                .setParameter("buyerName", buyer.getName()).getResultList();
    }

    @Override
    public synchronized Item addItem(String name, float price, User seller) throws RemoteException, UserException {
        checkUser(seller);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User storedSeller = em.createNamedQuery("findUserWithName", User.class)
                .setParameter("name", seller.getName()).getSingleResult();

        Item item = new Item(name, price, storedSeller);

        em.persist(item);

        em.getTransaction().commit();

        return item;
    }


    @Override
    public synchronized void buyItem(Item item, User buyer) throws RemoteException, RejectedException, UserException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        checkUser(buyer);

        User storedBuyer = em.createNamedQuery("findUserWithName", User.class)
                .setParameter("name", buyer.getName()).getSingleResult();
        Item storedItem = em.createNamedQuery("findItemWithID", Item.class)
                .setParameter("id", item.getId()).getSingleResult();

        bank.withdraw(buyer.getName(), item.getPrice());
        bank.deposit(item.getSeller().getName(), item.getPrice());
        storedItem.setbuyer(storedBuyer);

        em.getTransaction().commit();
    }

    private void checkUser(User user) throws RemoteException, UserException {
        if (!userHandler.userLoggedIn(user))
            throw new UserException("Not logged in");
    }


}
