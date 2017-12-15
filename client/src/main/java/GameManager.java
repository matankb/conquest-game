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
        frame = new JFrame("Conquest GameManager");

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        errorMessage = new JLabel();
        errorMessage.setBounds(400, 10, 30, 150);

        try {
            socket = IO.socket("http://localhost:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object... objects) {
                    //when connected runs this
                    account = new Account();
                    frame.add(account.panel);
                    frame.add(errorMessage);
                    account.login.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            account.sendLogin();
                        }
                    });
                    account.register.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            account.register();
                        }
                    });
                    //These should really be in the account class, but until it works with UI I won't bother.
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
            socket.connect();
        } catch (URISyntaxException e) {
            //swallow
        }
    }
}
