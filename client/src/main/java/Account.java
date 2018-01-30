import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

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
    private JLabel registerHeader;
    private JTextField enterUsernameField;
    private JTextField enterEmailField;
    private JPasswordField enterPasswordField;
    private JLabel enterUsername;
    private JLabel enterEmail;
    private JLabel enterPassword;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPassword;
    public JLabel registerErrorMessage;

    public Account() {

        panel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        setDefaults();

    }
    public void sendLogin() {
        if (accountField.getText().equals("") || passwordField.getPassword().equals("")) {
            throwLogInError();
            errorMessage.setText("Password or Email left blank.");
        } else if (accountField.getText().contains("@")){
            JSONObject data = new JSONObject();
            data.put("email", accountField.getText());
            data.put("password", Helper.charToString(passwordField.getPassword()));

            GameManager.socket.emit("accountInfo", data);

            passwordField.setText("");
        } else {
            throwLogInError();
            errorMessage.setText("Invalid username/password");
        }
    }
    public void sendRegister() {
        if (enterUsernameField.getText().equals("") || enterEmailField.getText().equals("") || enterPasswordField.getPassword().equals("")) {
            registerErrorMessage.setText("One or more fields left blank");
            throwRegisterError();
        } else {
            if (Helper.charToString(enterPasswordField.getPassword()).equals(Helper.charToString(confirmPasswordField.getPassword()))) {
                JSONObject data = new JSONObject();
                data.put("username", enterUsernameField.getText());
                data.put("email", enterEmailField.getText());
                data.put("password", Helper.charToString(enterPasswordField.getPassword()));

                GameManager.socket.emit("accountRegister", data);
            }
            else {
                registerErrorMessage.setText("Passwords do not match");
                throwRegisterError();
            }
        }
    }
    public void setDefaults() {
        title.setFont(Helper.getThemeFont(24));
        accountLabel.setFont(Helper.getThemeFont(20));
        passwordLabel.setFont(Helper.getThemeFont(20));
        login.setFont(Helper.getThemeFont(20));
        errorMessage.setFont(Helper.getThemeFont(14));
        loginHeader.setFont(Helper.getThemeFont(30));

        registerHeader.setFont(Helper.getThemeFont(30));
        enterEmail.setFont(Helper.getThemeFont(20));
        enterPassword.setFont(Helper.getThemeFont(20));
        enterUsername.setFont(Helper.getThemeFont(20));
        confirmPassword.setFont(Helper.getThemeFont(15));
        registerErrorMessage.setFont(Helper.getThemeFont(14));

        errorMessage.setVisible(false);
        addActionListeners();
    }
    public void addActionListeners() {
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendLogin();
            }
        });
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendRegister();
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }
    public void throwLogInError() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                errorMessage.setVisible(true);
                loginPanel.revalidate();
                loginPanel.repaint();
                try {
                    synchronized (this) {
                        wait(4000);
                    }
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
    public void throwRegisterError() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                registerErrorMessage.setVisible(true);
                registerPanel.revalidate();
                registerPanel.repaint();
                try {
                    synchronized (this) {
                        wait(4000);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                registerErrorMessage.setVisible(false);
                registerPanel.revalidate();
                registerPanel.repaint();
            }
        });
        thread.start();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        //NOT IN USE
        //panel = new VideoPanel("../src/images/background_image_login.jpg");
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}

