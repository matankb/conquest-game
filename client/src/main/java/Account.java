import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account {

    public JTextField accountField;
    public JPasswordField passwordField;
    public JButton login;
    public JLabel title;
    public JButton register;
    private JPanel panel;
    private JTabbedPane tabbedPane;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JLabel accountLabel;
    private JLabel passwordLabel;
    public JLabel errorMessage;
    private JLabel loginHeader;

    public Account() {

        panel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        setDefaults();

    }
    public void sendLogin() {
        if (accountField.getText().equals("") || passwordField.getPassword().equals("")) {
            throwError();
            errorMessage.setText("Password or Email left blank.");
        } else if (accountField.getText().contains("@")){
            JSONObject json = new JSONObject();
            json.put("email", accountField.getText());
            json.put("password", passwordField.getPassword());
            GameManager.socket.emit("accountInfo", json);
            accountField.setText("");
            passwordField.setText("");
            throwError();
            errorMessage.setText("Account is not correct");
        } else {
            throwError();
            errorMessage.setText("An error occurred");
        }
    }
    public void register() {
        panel.removeAll();
        register = new JButton("Register");
    }
    public void setDefaults() {
        title.setFont(Helper.getThemeFont(24));
        accountLabel.setFont(Helper.getThemeFont(20));
        passwordLabel.setFont(Helper.getThemeFont(20));
        login.setFont(Helper.getThemeFont(20));
        errorMessage.setFont(Helper.getThemeFont(10));
        loginHeader.setFont(Helper.getThemeFont(30));

        errorMessage.setVisible(false);
        addActionListeners();
    }
    public void addActionListeners() {
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendLogin();
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }
    public void throwError() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                errorMessage.setVisible(true);
                loginPanel.revalidate();
                loginPanel.repaint();
                try {
                    wait(4000);
                } catch (InterruptedException e) {
                    //swallow exception
                }
                errorMessage.setVisible(false);
                loginPanel.revalidate();
                loginPanel.repaint();
            }
        });
        thread.start();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //NOT IN USE
        //panel = new ImagePanel("../src/images/background_image_login.jpg");
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}

