package networkMarket.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

/**
 * Created by daseel on 11/22/16.
 */
public class StoreController {

    private User user;
    private MarketPlace market;

    @FXML
    Button sellButton;

    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
    }


    @FXML
    public void switchToSellView() {


    }

}
