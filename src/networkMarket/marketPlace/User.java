package networkMarket.marketPlace;

import networkMarket.interfaces.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by daseel on 2016-11-11.
 */


@Entity(name = "User")
public class User implements Serializable {
    private Account bankAccount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="username")
    private String name;

    @Column(name="password")
    private String password;

    public User() {

    }

    public User(String name, String password) throws RemoteException {
        this.name = name;
        this.password = password;
    }

    public Account getBankAccount() throws RemoteException {
        return bankAccount;
    }

    public String getName() {
        return name;
    }

    public String getPassword() throws RemoteException {
        return password;
    }

    public void setBankAccount(Account acc) throws RemoteException {
        bankAccount = acc;
    }
}
