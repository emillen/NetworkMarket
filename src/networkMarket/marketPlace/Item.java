package networkMarket.marketPlace;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.RemoteException;

@Entity(name = "Item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User buyer;

    protected Item() {
    }

    public Item(String name, float price, User seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public User getSeller() {
        return seller;
    }

    public boolean isSold() {
        return buyer == null;
    }

    public void setbuyer(User buyer) {
        this.buyer = buyer;
    }
}
