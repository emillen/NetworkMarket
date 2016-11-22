package networkMarket.interfaces;

/**
 * Created by daseel on 11/22/16.
 */
public interface UserHandler {

    User logIn(String userName, String password);

    void register(String userName, String password);

    void unregister(String userName, String password);
}
