package networkMarket.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import networkMarket.client.services.AddBankService;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

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

        if(usernameField.getText().equals(""))
            return;

        AddBankService service = new AddBankService(user, usernameField.getText());
        service.start();
    }
}
