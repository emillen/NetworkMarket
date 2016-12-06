package networkMarket.marketPlace;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "findAllItems",
                query = "SELECT item FROM Item item WHERE item.buyer IS NULL"
        ),
        @NamedQuery(
                name = "findItemWithID",
                query = "SELECT item FROM Item item WHERE item.id = :id"
        ),
        @NamedQuery(
                name = "findItemsFromSeller",
                query = "SELECT item FROM Item item WHERE item.seller.name LIKE :sellerName"
        ),
        @NamedQuery(
                name = "findItemsFromBuyer",
                query = "SELECT item FROM Item item WHERE item.seller.name LIKE :buyerName"
        )
})

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private User buyer;

    protected Item() {
    }

    long getId() {
        return id;
    }

    public Item(String name, float price, User seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public User getSeller() {
        return seller;
    }

    public boolean isSold() {
        return buyer != null;
    }

    public void setbuyer(User buyer) {
        this.buyer = buyer;
    }
}
