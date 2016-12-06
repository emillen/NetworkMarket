package networkMarket.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import networkMarket.client.services.GetBoughtItemsService;
import networkMarket.client.services.GetItemsService;
import networkMarket.client.services.GetSoldItemsService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.MarketPlace;
import networkMarket.marketPlace.Item;
import networkMarket.marketPlace.User;

import java.net.URL;
import java.util.List;


/**
 * Created by daseel on 12/6/16.
 */
public class MyItemsController implements Controller {

    private User user;
    private MarketPlace market;
    private List<Item> soldItems;
    private List<Item> boughtItems;
    @FXML
    Button backButton;
    @FXML
    ListView<String> soldItemList;
    @FXML
    ListView<String> boughtItemList;

    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
        updateItems();
    }


    @FXML
    public void back() {

        Stage stage = (Stage) soldItemList.getScene().getWindow();
        URL url = getClass().getResource("../views/storeView.fxml");
        ViewSwapper.swap(user, market, stage, url);
    }

    private ObservableList<String> getSoldObservableList(List<Item> items) {

        ObservableList<String> list = FXCollections.observableArrayList();
        for (Item i : items) {
            String isSold = "";
            if (i.isSold())
                isSold = " SOLD";

            list.add(i.getName() + " " + i.getPrice() + "kr" + isSold);
        }

        return list;
    }

    private ObservableList<String> getBoughtObservableList(List<Item> items) {

        ObservableList<String> list = FXCollections.observableArrayList();
        for (Item i : items) {
            list.add(i.getName() + " " + i.getPrice() + "kr");
        }

        return list;
    }

    private void displaySoldItems() {
        ObservableList<String> list = getSoldObservableList(soldItems);

        soldItemList.setItems(list);
    }

    private void displayBoughtItems() {
        ObservableList<String> list = getBoughtObservableList(boughtItems);

        boughtItemList.setItems(list);
    }

    private void updateItems() {
        GetSoldItemsService soldService = new GetSoldItemsService(user, market);
        soldService.setOnSucceeded(new GetSoldItemSuccess());
        soldService.setOnFailed(new GetSoldItemFailure());
        soldService.start();

        GetBoughtItemsService boughtService = new GetBoughtItemsService(user, market);
        boughtService.setOnSucceeded(new GetBoughtItemSuccess());
        boughtService.setOnFailed(new GetBoughtItemFailure());
        boughtService.start();
    }

    private class GetSoldItemSuccess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            soldItems = (List<Item>) workerStateEvent.getSource().getValue();
            displaySoldItems();
        }
    }

    private class GetSoldItemFailure implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            Stage stage = (Stage) soldItemList.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");
            ViewSwapper.swap(null, market, stage, url);

        }
    }


    private class GetBoughtItemSuccess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            boughtItems = (List<Item>) workerStateEvent.getSource().getValue();
            displayBoughtItems();
        }
    }

    private class GetBoughtItemFailure implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            Stage stage = (Stage) soldItemList.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");
            ViewSwapper.swap(null, market, stage, url);

        }
    }
}
