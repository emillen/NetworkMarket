package networkMarket.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import networkMarket.client.services.GetItemsService;
import networkMarket.client.views.ViewSwapper;
import networkMarket.interfaces.Item;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;

import java.net.URL;
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
    ListView<String> itemList;

    @Override
    public void init(User user, MarketPlace market) {
        this.user = user;
        this.market = market;

        GetItemsService service = new GetItemsService(user, market);
        service.setOnSucceeded(new GetItemSuccess());
        service.setOnFailed(new GetItemFailure());
        service.start();
    }


    @FXML
    public void switchToSellView() {

        // TODO: 11/22/16 Check that user has bank account, else make him create one

        URL url = getClass().getResource("../views/sellView.fxml");
        Stage stage = (Stage) sellButton.getScene().getWindow();

        ViewSwapper.swap(user, market, stage, url);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Methods and classes for displaying items
    ///////////////////////////////////////////////////////////////////////////

    private class GetItemSuccess implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            items = (List<Item>) workerStateEvent.getSource().getValue();
        }
    }

    private class GetItemFailure implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {

            Stage stage = (Stage) itemList.getScene().getWindow();
            URL url = getClass().getResource("../views/loginView.fxml");
            ViewSwapper.swap(user, market, stage, url);

        }
    }

    
}
