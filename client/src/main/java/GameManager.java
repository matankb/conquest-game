import javax.swing.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class GameManager {

    static Socket socket;
    static JFrame frame;
    static Account account;
    static JLabel errorMessage;
    static String sessionToken;

    public static void main(String[] args) {
        initFrame();
        try {
            socket = IO.socket("http://localhost:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object... objects) {
                    //when connected runs this
                    account = new Account();
                    replaceContentPane(account.getPanel());
                }
            });
            socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                public void call(Object... objects) {
                    frame.add(errorMessage);
                    errorMessage.setText("Failed to connect to the server.");
                }
            });
            socket.on("loginSuccess", new Emitter.Listener() {
                public void call(Object... objects) {
                    JSONObject data = (JSONObject) objects[0];
                    try {
                        sessionToken = (String) data.get("token");
                        EnterGame enterGame = new EnterGame((String) data.get("username"));
                        //creates page to enter a game from (i.e., private game or random public game)
                    } catch (JSONException e) {
                        System.out.println(e.toString());
                    }
                }
            });
            socket.on("loginFailure", new Emitter.Listener() {
                public void call(Object... objects) {
                    JSONObject data = (JSONObject) objects[0];
                    try {
                        account.errorMessage.setText((String) data.get("message"));
                    } catch (JSONException e) {
                        System.out.println(e.toString());
                    }
                    account.throwLogInError();
                }
            });
            socket.on("accountRegisterSuccess", new Emitter.Listener() {
                public void call(Object... objects) {
                    //this code will need to later on wait for player to confirm email before switching.
                    JSONObject data = (JSONObject) objects[0];
                    try {
                        sessionToken = (String) data.get("token");
                        EnterGame enterGame = new EnterGame((String) data.get("username"));
                        //creates page to enter a game from (i.e., private game or random public game)
                    } catch (JSONException e) {
                        System.out.println(e.toString());
                    }
                }
            });
            socket.on("accountRegisterFailure", new Emitter.Listener() {
                public void call(Object... objects) {
                    JSONObject data = (JSONObject) objects[0];
                    try {
                        account.registerErrorMessage.setText((String) data.get("message"));
                    } catch (JSONException e) {
                        System.out.println(e.toString());
                    }
                    account.throwRegisterError();
                }
            });
            socket.on("initialGameState", new Emitter.Listener() {
                public void call(Object... objects) {
                    //set up the beginning game state
                    Game game = new Game();
                }
            });
            socket.on("beginGame", new Emitter.Listener() {
                public void call(Object... objects) {
                    //activate ActionListeners in Game to begin the game
                    Game.beginGame();
                }
            });
            socket.on("newGameState", new Emitter.Listener() {
                public void call(Object... objects) {
                    //something that updates the game map/state/whatever
                }
            });
            socket.connect();
        } catch (URISyntaxException e) {
            //swallow
        }
    }
    public static void replaceContentPane(JPanel panel) {
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.validate();
        frame.repaint();
    }
    public static void initFrame() {
        frame = new JFrame("Provincial Takeover");

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
