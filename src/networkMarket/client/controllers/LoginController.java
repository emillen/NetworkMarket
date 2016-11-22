package networkMarket.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import networkMarket.client.services.RegisterService;
import networkMarket.interfaces.MarketPlace;

public class LoginController {


    ///////////////////////////////////////////////////////////////////////////
    // Properties for the login tab
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    TextField loginUsernameField;
    @FXML
    TextField loginPasswordField;
    @FXML
    Button loginButton;


    ///////////////////////////////////////////////////////////////////////////
    // Properties for the register tab
    ///////////////////////////////////////////////////////////////////////////

    @FXML
    TextField registerUsernameField;
    @FXML
    TextField registerPasswordField;
    @FXML
    Button registerButton;


    ///////////////////////////////////////////////////////////////////////////
    // Initialization
    ///////////////////////////////////////////////////////////////////////////
    private MarketPlace market;

    public void init(MarketPlace market) {
        this.market = market;
    }


    ///////////////////////////////////////////////////////////////////////////
    // methods and classes for the login tab
    ///////////////////////////////////////////////////////////////////////////


    @FXML
    public void loginUsernameAction(){

    }

    @FXML
    public void login() {

    }



    ///////////////////////////////////////////////////////////////////////////
    // Methods and classes for the register tab
    ///////////////////////////////////////////////////////////////////////////

    @FXML
    public void registerUsernameAction(){

    }

    @FXML
    public void register() {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();

        RegisterService service = new RegisterService(market, username, password);
        service.start();

    }

}
