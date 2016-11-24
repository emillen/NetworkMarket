package networkMarket.client.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import networkMarket.interfaces.MarketPlace;
import networkMarket.interfaces.User;
import networkMarket.interfaces.Wish;

import java.util.List;

/**
 * Created by daseel on 11/24/16.
 */
public class CheckWishesService extends Service<Wish> {

    private User user;
    private MarketPlace market;


    public CheckWishesService(User user, MarketPlace market) {
        this.user = user;
        this.market = market;
        this.setOnSucceeded(new SuccessHandler());
        this.setOnFailed(new FailHandler());
    }

    @Override
    protected Task<Wish> createTask() {


        return new Task<Wish>() {
            @Override
            protected Wish call() throws Exception {
                Thread.sleep(1000);
                List<Wish> myWishList = market.getStore().getMyWishes(user);
                Wish foundWish = null;
                if(myWishList != null)
                    for (Wish w : myWishList) {
                        if (w.beenFound()) {
                            System.out.println("Hueheuhe");
                            foundWish = w;
                            market.getStore().removeFromWishList(w, user);
                            break;
                        }
                    }
                    return foundWish;
            }
        };
    }

    private class SuccessHandler implements EventHandler<WorkerStateEvent> {

        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            Wish wish = (Wish) workerStateEvent.getSource().getValue();
            if (wish != null) {
                try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Hey, " + user.getName() + "!");
                    alert.setContentText(wish.getItemName() + " for less than " + wish.getPrice() + "kr has been found");

                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            new CheckWishesService(user, market).start();
        }
    }

    private class FailHandler implements EventHandler<WorkerStateEvent> {
        @Override
        public void handle(WorkerStateEvent workerStateEvent) {
            workerStateEvent.getSource().getException().printStackTrace();
        }
    }
}
