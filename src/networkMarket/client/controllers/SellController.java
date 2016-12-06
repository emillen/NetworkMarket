package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networkMarket.client.services.CheckSoldService;
import networkMarket.client.services.SellService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.Item;
import networkMarket.marketPlace.User;

import java.net.URL;

/**
 * Created by daseel on 11/22/16.
 */
public class SellController implements Controller {

    private User user;
    private MarketPlace market;


    @FXML
    TextField nameField;

    @FXML
    TextField priceField;

    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }

    @FXML
    public void sell() {
        String name = nameField.getText();
        Float price = Float.parseFloat(priceField.getText());

        SellService service = new SellService(name, price, user, market);
        service.setOnSucceeded(new SuccessHandler());
        service.setOnFailed(new FailHandler());

        service.start();

    }


    private class SuccessHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            CheckSoldService service = new CheckSoldService((Item) workerStateEvent.getSource().getValue());
            service.setOnSucceeded(new ItemIsSoldHandler());
            service.setOnFailed(new ItemIsSoldFailHandler());
            service.start();
            Stage stage = (Stage) nameField.getScene().getWindow();
            URL url = getClass().getResource("../views/storeView.fxml");
            ViewSwapper.swap(user, market, stage, url);
        }
    }

    private class FailHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {


            Stage stage = (Stage) nameField.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");
            ViewSwapper.swap(null, market, stage, url);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Handlers for checking if the item is sold
    ///////////////////////////////////////////////////////////////////////////
    private class ItemIsSoldHandler implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            Item item = (Item) workerStateEvent.getSource().getValue();
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Hey, " + user.getName() + "!");
                alert.setContentText(item.getName() + " " + item.getPrice() + "kr has been sold");

                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ItemIsSoldFailHandler implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            System.out.println("The item could not be sold ");
        }
    }
}
