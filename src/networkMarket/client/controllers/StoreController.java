package networkMarket.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import networkMarket.bank.exceptions.RejectedException;
import networkMarket.client.services.BuyService;
import networkMarket.client.services.GetItemsService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daseel on 11/22/16.
 */
public class StoreController implements Controller {

    private User user;
    private MarketPlace market;
    private List<Item> items;


    @FXML
    Button sellButton;
    @FXML
    Button wishButton;
    @FXML
    ListView<String> itemList;
    @FXML
    Text warningText;


    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
        this.items = new ArrayList<>();
        updateView();
    }

    @FXML
    private void updateView() {
        GetItemsService service = new GetItemsService(user, market);
        service.setOnSucceeded(new GetItemSuccess());
        service.setOnFailed(new GetItemFailure());
        service.start();
    }

    @FXML
    public void switchToWishView() {
        URL url = getClass().getResource("../views/makeWishView.fxml");
        Stage stage = (Stage) sellButton.getScene().getWindow();

        ViewSwapper.swap(user, market, stage, url);
    }

    @FXML
    public void switchToSellView() {

        if (!hasBankAccount(user)) {
            Stage stage = (Stage) sellButton.getScene().getWindow();
            URL url = getClass().getResource("../views/addBankAccountView.fxml");
            ViewSwapper.swap(user, market, stage, url);
            return;
        }

        URL url = getClass().getResource("../views/sellView.fxml");
        Stage stage = (Stage) sellButton.getScene().getWindow();

        ViewSwapper.swap(user, market, stage, url);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Methods and classes for displaying items
    ///////////////////////////////////////////////////////////////////////////

    private void displayItems() {
        ObservableList<String> list = getObservableList(items);

        itemList.setItems(list);
        itemList.setOnMouseClicked(new ListClickHandler());
    }


    private ObservableList<String> getObservableList(List<Item> items) {

        ObservableList<String> list = FXCollections.observableArrayList();
        for (Item i : items) {
            try {
                list.add(i.getName() + " " + i.getPrice() + "kr");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private class GetItemSuccess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            items = (List<Item>) workerStateEvent.getSource().getValue();
            displayItems();
        }
    }

    private class GetItemFailure implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            Stage stage = (Stage) itemList.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");
            ViewSwapper.swap(null, market, stage, url);

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Classes for buying items
    ///////////////////////////////////////////////////////////////////////////

    private class ListClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2) {

                if (!hasBankAccount(user)) {
                    Stage stage = (Stage) itemList.getScene().getWindow();
                    URL url = getClass().getResource("../views/addBankAccountView.fxml");
                    ViewSwapper.swap(user, market, stage, url);
                }
                int index = itemList.getSelectionModel().getSelectedIndices().get(0);

                BuyService service = new BuyService(items.get(index), user, market);
                service.setOnSucceeded(new BuyItemSuccess());
                service.setOnFailed(new BuyItemFail());
                service.start();
            }
        }
    }

    private boolean hasBankAccount(User user) {

        boolean hasBankAccount = true;

        try {
            if (user.getBankAccount() == null) {
                hasBankAccount = false;
            }

        } catch (RemoteException e) {
            switchToLoginView();
        }

        return hasBankAccount;
    }

    private class BuyItemSuccess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Stage stage = (Stage) itemList.getScene().getWindow();
            URL url = getClass().getResource("../views/storeView.fxml");
            ViewSwapper.swap(user, market, stage, url);
        }
    }

    private class BuyItemFail implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            System.out.println(workerStateEvent.getSource().getException().getMessage());
            if (workerStateEvent.getSource().getException() instanceof RejectedException) {

                warningText.setText("Not enough cash dude");
                warningText.setVisible(true);
            } else {

                switchToLoginView();
            }
        }
    }

    private void switchToLoginView() {
        Stage stage = (Stage) itemList.getScene().getWindow();
        URL url = getClass().getResource("../views/loginView.fxml");
        ViewSwapper.swap(null, market, stage, url);
    }
}
