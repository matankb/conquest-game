import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;

public class Account {

    public JTextField accountField;
    public JPasswordField passwordField;
    public JButton login;
    public JLabel title;
    public JButton register;
    public JButton sendRegister;
    private JPanel panel;
    private JTabbedPane tabbedPane;
    private JPanel Login;
    private JPanel Register;

    public Account() {

        panel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));

    }
    public void sendLogin() {
        if (accountField.getText().equals("") || passwordField.getPassword().equals("")) {
            GameManager.errorMessage.setText("Password or Email left blank.");
        } else if (accountField.getText().contains("@")){
            JSONObject json = new JSONObject();
            json.put("email", accountField.getText());
            json.put("password", passwordField.getPassword());
            GameManager.socket.emit("accountInfo", json);
            accountField.setText("");
            passwordField.setText("");
            GameManager.errorMessage.setText("Sending Login...");
        } else {
            GameManager.errorMessage.setText("Account invalid. Please check you put in a valid email address.");
        }
    }
    public void register() {
        panel.removeAll();
        sendRegister = new JButton("Register");
    }

    public JPanel getPanel() {
        return panel;
    }
}

