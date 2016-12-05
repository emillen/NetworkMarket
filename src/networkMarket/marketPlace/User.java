package networkMarket.marketPlace;

import networkMarket.interfaces.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by daseel on 2016-11-11.
 */


@Entity(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> soldItems;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Item> boughtItems;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account getBankAccount() {
        //  return bankAccount;
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
