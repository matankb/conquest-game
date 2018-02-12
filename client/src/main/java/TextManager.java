import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextManager extends JComponent{
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextArea textArea1;

    public TextManager() {
        setDefaults();
        add("garbo kid", "xX_69_69_69_69_Xx"); //test
    }
    public void add(String text, String username) {
        scrollPane.add(new FontedLabel(username, text));
        scrollPane.revalidate();
    }

    private void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

        panel.setBorder(BorderFactory.createTitledBorder("Chat Room"));
    }
}
