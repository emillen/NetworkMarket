package networkMarket.marketPlace;

import networkMarket.interfaces.MarketPlace;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by daseel on 2016-11-11.
 */
public class Server {

    public Server() {
        try {
            MarketPlace markPlace = new MarketPlaceServant();

            try {
                LocateRegistry.getRegistry(1099).list();
            } catch (RemoteException e) {
                LocateRegistry.createRegistry(1099);
            }

            Naming.rebind("market", markPlace);


            System.out.println("We are ready to go fgt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
