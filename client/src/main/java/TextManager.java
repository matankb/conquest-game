import javax.swing.*;
import java.awt.*;

public class TextManager extends JComponent{
    private JPanel panel;
    private JScrollPane scrollPane;

    public TextManager() {
        setDefaults();
        add("garbo kid", "xX_69_69_69_69_Xx"); //test
    }
    public void add(String text, String username) {
        scrollPane.add(new FontedLabel(username, text));
    }

    private void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
