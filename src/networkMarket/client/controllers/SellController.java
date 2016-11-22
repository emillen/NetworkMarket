package networkMarket.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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

    }
}
