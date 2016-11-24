package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networkMarket.client.services.MakeWishService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import javax.swing.text.View;
import java.net.URL;


/**
 * Created by daseel on 11/24/16.
 */
public class MakeWishController implements Controller {

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
    public void makeWish() {

        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        MakeWishService service = new MakeWishService(name, price, user, market);
        service.setOnSucceeded(new SuccessHandler());
        service.setOnFailed(new FailHandler());
        service.start();
    }


    private class SuccessHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Stage stage = (Stage) priceField.getScene().getWindow();
            URL url = getClass().getResource("../views/storeView.fxml");

            ViewSwapper.swap(user, market, stage, url);
        }
    }

    private class FailHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Stage stage = (Stage) priceField.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");

            ViewSwapper.swap(user, market, stage, url);
        }
    }
}
