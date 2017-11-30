import io.socket.emitter.Emitter;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class Account {

    public JTextField accountField;
    public JPasswordField passwordField;
    public JButton login;

    public Account() {

        login = new JButton("login");
        accountField = new JTextField();
        passwordField = new JPasswordField();

        login.setBounds(20, 10, 60, 30);
        accountField.setBounds(290, 465, 150, 30);
        passwordField.setBounds(290, 435, 150, 30);

        Game.frame.add(login);
        Game.frame.add(accountField);
        Game.frame.add(passwordField);

    }
    public void sendLogin() {
        JSONObject json = new JSONObject();
        json.put("username", accountField.getText());
        json.put("password", passwordField.getPassword());
        Game.socket.emit("accountInfo", json);
    }
}

