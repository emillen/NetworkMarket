package networkMarket.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networkMarket.client.controllers.LoginController;
import networkMarket.interfaces.MarketPlace;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Client extends Application {
    private static MarketPlace market;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/loginView.fxml"));

        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.init(market);
        stage.setTitle("Market Place, yo");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public static void main(String[] args) {

        try {
            try {
                LocateRegistry.getRegistry(1099).list();

            } catch (RemoteException e) {
                LocateRegistry.createRegistry(1099);
                System.out.println(e.getMessage());
            }

            market = (MarketPlace) Naming.lookup("MarketPlace");
            System.out.println("We are ready to go bitch");
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
