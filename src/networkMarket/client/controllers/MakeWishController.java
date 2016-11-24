package networkMarket.client.controllers;

import javafx.fxml.FXML;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.awt.*;

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
        
    }
}
