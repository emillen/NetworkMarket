package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networkMarket.client.services.AddBankService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.net.URL;

/**
 * Created by daseel on 11/23/16.
 */
public class AddBankAccountController implements Controller {

    private User user;
    private MarketPlace market;

    @FXML
    TextField usernameField;
    @FXML
    Button button;

    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }

    @FXML
    public void createAccount() {

        if (usernameField.getText().equals(""))
            return;

        AddBankService service = new AddBankService(user, usernameField.getText());
        service.setOnSucceeded(new SuccessHandler());
        service.setOnFailed(new FailHandler());
        service.start();
    }

    private class SuccessHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            URL url = getClass().getResource("../views/storeView.fxml");

            ViewSwapper.swap(user, market, stage, url);
        }
    }

    private class FailHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");

            ViewSwapper.swap(user, market, stage, url);
        }
    }
}
