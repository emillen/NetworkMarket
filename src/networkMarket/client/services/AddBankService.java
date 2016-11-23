package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.Bank;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by daseel on 11/23/16.
 */
public class AddBankService extends Service<Void> {

    private User user;
    private String accountName;

    public AddBankService(User user, String accountName) {

        this.user = user;
        this.accountName = accountName;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                LocateRegistry.getRegistry(1099).list();
                LocateRegistry.createRegistry(1099);
                Bank bank = (Bank) Naming.lookup("Nordea");
                System.out.println("Bank is on");

                try {
                    user.setBankAccount(bank.newAccount(accountName));
                } catch (RejectedException e) {
                    user.setBankAccount(bank.getAccount(accountName));
                }

                return null;
            }
        };
    }
}
