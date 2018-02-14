import javax.swing.*;
import java.awt.*;

public class TextManager extends JComponent{
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private JTextField inputField;

    public TextManager() {
        setDefaults();
        add("garbo kid", "xX_69_69_69_69_Xx"); //test
    }
    public void add(String text, String username) {
        textPane.setText(textPane.getText() + username + ": " + text + "\n");
        textPane.revalidate();
        textPane.repaint();
    }

    private void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

}
