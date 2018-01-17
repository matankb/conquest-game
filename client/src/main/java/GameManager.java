import javax.swing.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GameManager {

    static Socket socket;
    static JFrame frame;
    static Account account;
    static JLabel errorMessage;
    static String sessionToken;

    public static void main(String[] args) {
        initFrame();

        errorMessage = new JLabel();
        errorMessage.setBounds(400, 10, 30, 150);

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
                    errorMessage.setText("Failed to connect to the server.");
                }
            });
            socket.on("loginSuccess", new Emitter.Listener() {
                public void call(Object... objects) {
                    errorMessage.setText("Login successful.");
                    //unnecessary later on, because it should be removed afterwards for when the games list loads.
                    frame.remove(account.login);
                    frame.remove(account.accountField);
                    frame.remove(account.passwordField);

                    JSONObject data = (JSONObject) objects[0];
                    sessionToken = (String) data.get("token");

                    ArrayList<GameOption> gamesList = Helper.makeArray((JSONArray) data.get("games"));
                    EnterGame enterGame = new EnterGame(gamesList);
                    //This is creating the option after logging in to create a game or join a game.
                }
            });
            socket.on("loginFailure", new Emitter.Listener() {
                public void call(Object... objects) {
                    errorMessage.setText("Login unsuccessful. Please check that you entered the correct username and/or password.");
                }
            });
            socket.on("initialGameState", new Emitter.Listener() {
                public void call(Object... objects) {
                    //set up the beginning game state
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
        frame = new JFrame("Conquest GameManager");

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
