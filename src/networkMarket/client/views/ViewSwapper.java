package networkMarket.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networkMarket.client.controllers.Controller;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.User;

import java.io.IOException;
import java.net.URL;

/**
 * Created by daseel on 2016-11-10.
 */
public class ViewSwapper {

    public static void swap(User user, MarketPlace market, Stage stage, URL url) {

        FXMLLoader loader = new FXMLLoader(url);
        Parent root;
        try {
            root = (Parent) loader.load();
            Controller controller = (Controller) loader.getController();
            controller.init(user, market);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}