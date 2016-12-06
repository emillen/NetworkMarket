package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import networkMarket.bank.exceptions.RejectedException;
import networkMarket.interfaces.Account;
import networkMarket.interfaces.Bank;
import networkMarket.marketPlace.User;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by daseel on 11/23/16.
 */
public class DepositToBankService extends Service<Void> {

    private User user;
    private Float toDeposit;

    public DepositToBankService(User user, float toDeposit) {
        this.user = user;
        this.toDeposit = toDeposit;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                LocateRegistry.getRegistry(1099).list();
                Bank bank = (Bank) Naming.lookup("Nordea");
                System.out.println("Bank is on");
                Account account;
                try {
                    account = bank.newAccount(user.getName());
                } catch (RejectedException e) {
                    account = bank.getAccount(user.getName());
                }

                account.deposit(toDeposit);

                return null;
            }
        };
    }
}
