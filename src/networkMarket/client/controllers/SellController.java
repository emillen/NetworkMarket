package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import networkMarket.client.services.SellService;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

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
        double price = Double.parseDouble(priceField.getText());

        SellService service = new SellService(name, price, user, market);
        service.setOnSucceeded(new SuccessHandler());
        service.setOnFailed(new FailHandler());

        service.start();

    }

    // TODO: 11/22/16 Write eventhandlers


    private class SuccessHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

        }
    }

    private class FailHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

        }
    }
}
