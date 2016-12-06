package networkMarket.marketPlace;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "findUserWithName",
                query = "SELECT user FROM User user WHERE user.name LIKE :name"
        ),
        @NamedQuery(
                name = "deleteUserWithName",
                query = "DELETE FROM User user WHERE user.name LIKE :name"
        ),
        @NamedQuery(
                name = "findAllUsers",
                query = "SELECT user FROM User user"
        )
})

@Entity(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
