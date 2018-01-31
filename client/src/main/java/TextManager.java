import javax.swing.*;
import java.awt.*;

public class TextManager extends JComponent{
    private JLabel userText;
    private JPanel panel;

    public TextManager() {
        setDefaults();
        add("garbo kid", "xX_69_69_69_69_Xx"); //test
    }
    public void add(String text, String username) {
        userText = new FontedLabel(username, text);
        panel.add(userText);
    }

    private void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
