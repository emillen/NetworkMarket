package networkMarket.marketPlace;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.RemoteException;

@Entity(name = "Item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller", nullable = false)
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer", nullable = true)
    private User buyer;

    protected Item() {
    }

    public Item(String name, float price, User seller) throws RemoteException {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public synchronized double getPrice() throws RemoteException {
        return price;
    }

    public synchronized String getName() throws RemoteException {
        return name;
    }

    public synchronized User getSeller() throws RemoteException {
        return seller;
    }

    public boolean isSold() throws RemoteException {
        return buyer == null;
    }
}
