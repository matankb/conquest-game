import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterGame {

    String username;

    private JPanel panel;
    private JLabel title;
    private JLabel paddingLabel;
    private JButton joinPrivateGame;
    private JButton joinPublicGame;
    private JTextField privateGameIDField;
    private JButton joinGameButton;
    private JLabel errorMessage;
    private JButton createPrivateGame;

    public EnterGame(String username) {
        this.username = username;
        GameManager.replaceContentPane(panel);
        setDefaults();
        addActionListeners();
        System.out.println(username);
    }
    public void setDefaults() {
        title.setFont(Helper.getThemeFont(60));
        paddingLabel.setFont(Helper.getThemeFont(60));
        joinPrivateGame.setFont(Helper.getThemeFont(20));
        joinPublicGame.setFont(Helper.getThemeFont(20));
        joinGameButton.setFont(Helper.getThemeFont(15));
        createPrivateGame.setFont(Helper.getThemeFont(20));
        errorMessage.setFont(Helper.getThemeFont(10));
    }
    public void addActionListeners() {
        joinPrivateGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                joinGameButton.setVisible(true);
                privateGameIDField.setVisible(true);
                errorMessage.setVisible(false);
            }
        });
        joinPublicGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                joinGameButton.setVisible(false);
                privateGameIDField.setVisible(false);
                errorMessage.setVisible(false);

                SessionJSON data = new SessionJSON();
                GameManager.socket.emit("joinPublicGame", data);
            }
        });
        joinGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!privateGameIDField.getText().equals("")) {
                    GameManager.socket.emit("joinPrivateGame", privateGameIDField.getText());
                    privateGameIDField.setText("");
                    privateGameIDField.setVisible(false);
                    joinGameButton.setVisible(false);
                    errorMessage.setVisible(false);
                } else {
                    errorMessage.setText("Not a valid game ID");
                    errorMessage.setVisible(true);
                }
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel();
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

        panel.setBorder(BorderFactory.createTitledBorder("Welcome, " + username));
    }
}
