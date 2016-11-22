package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import networkMarket.client.services.LoginService;
import networkMarket.client.services.RegisterService;
import networkMarket.interfaces.MarketPlace;

public class LoginController {


    @FXML
    TabPane tabPane; // To use for switching tabs

    ///////////////////////////////////////////////////////////////////////////
    // Properties for the login tab
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    TextField loginUsernameField;
    @FXML
    PasswordField loginPasswordField;
    @FXML
    Button loginButton;


    ///////////////////////////////////////////////////////////////////////////
    // Properties for the register tab
    ///////////////////////////////////////////////////////////////////////////

    @FXML
    TextField registerUsernameField;
    @FXML
    PasswordField registerPasswordField;
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
    public void loginUsernameAction() {
        loginPasswordField.requestFocus();
    }

    @FXML
    public void login() {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        LoginService service = new LoginService(market, username, password);
        service.start();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Methods and classes for the register tab
    ///////////////////////////////////////////////////////////////////////////

    @FXML
    public void registerUsernameAction() {
        registerPasswordField.requestFocus();
    }

    @FXML
    public void register() {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();
        RegisterService service = new RegisterService(market, username, password);
        service.setOnSucceeded(new RegisterSuccess());
        service.setOnFailed(new RegisterFail());
        service.start();

    }


    private class RegisterSuccess implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            registerUsernameField.setText("");
            registerPasswordField.setText("");
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(0);
        }
    }

    private class RegisterFail implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            System.out.println(workerStateEvent.getSource().getException().getMessage());
        }
    }
}
