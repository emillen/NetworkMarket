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

            try {
                LocateRegistry.getRegistry(1099).list();
            } catch (RemoteException e) {
                LocateRegistry.createRegistry(1099);
            }

            MarketPlace market = new MarketPlaceServant();

            Naming.bind("MarketPlace", market);

            System.out.println("Server is ready to go");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
