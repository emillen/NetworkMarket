package networkMarket.client.controllers;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import networkMarket.marketPlace.UserServant;
import networkMarket.client.services.LoginService;
import networkMarket.client.services.RegisterService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.net.URL;

public class LoginController implements Controller {


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

    @Override
    public void init(User user, MarketPlace market) {
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
        service.setOnSucceeded(new LoginSucess());
        service.setOnFailed(new LoginFail());
        service.start();
    }

    private class LoginSucess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            User user = (User) workerStateEvent.getSource().getValue();
            Stage stage = (Stage) tabPane.getScene().getWindow();
            URL url = getClass().getResource("../views/storeView.fxml");

            ViewSwapper.swap(user, market, stage, url);
        }
    }

    private class LoginFail implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            System.out.println(workerStateEvent.getSource().getException().getMessage());
        }
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
