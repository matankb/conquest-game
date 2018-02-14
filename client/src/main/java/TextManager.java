import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextManager extends JComponent implements KeyListener{
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private JTextField inputField;

    public TextManager() {
        setDefaults();
        GameManager.socket.on("recieveChatMessage", new Emitter.Listener() {
            public void call(Object... objects) {
                SessionJSON data = (SessionJSON) objects[0];
                try {
                    add((String) data.get("username"), (String) data.get("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void add(String username, String text) {
        textPane.setText(textPane.getText() + username + ": " + text + "\n");
        textPane.revalidate();
        textPane.repaint();
    }
    private void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setBorder(BorderFactory.createTitledBorder("Chat Room"));
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            SessionJSON data = new SessionJSON();
            try {
                data.put("message", inputField.getText());
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
            GameManager.socket.emit("sendChatMessage", data);
        }
    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }
}
