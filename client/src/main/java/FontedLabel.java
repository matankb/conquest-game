import javax.swing.*;

public class FontedLabel extends JLabel {
    public FontedLabel(String username, String text) {
        super();
        super.setText(username + ": " + text);
        super.setFont(Helper.getThemeFont(10));
    }
}
