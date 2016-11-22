package networkMarket.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.net.URL;

/**
 * Created by daseel on 11/22/16.
 */
public class StoreController implements Controller {

    private User user;
    private MarketPlace market;

    @FXML
    Button sellButton;

    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }


    @FXML
    public void switchToSellView() {

        // TODO: 11/22/16 Check that user has bank account, else make him create one

        URL url = getClass().getResource("../views/sellView.fxml");
        Stage stage = (Stage) sellButton.getScene().getWindow();

        ViewSwapper.swap(user, market, stage, url);
    }

    // TODO: 11/22/16 Write items to the screen
}
