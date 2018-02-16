import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmExit {

    JPanel panel;
    private JButton yes;
    private JButton no;
    private JLabel title;
    private Container oldContentPane;

    public ConfirmExit() {
        oldContentPane = GameManager.frame.getContentPane();
        GameManager.replaceContentPane(panel);
        setDefaults();
    }
    public void setDefaults() {
        yes.setFont(Helper.getThemeFont(15));
        no.setFont(Helper.getThemeFont(15));
        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameManager.replaceContentPane((JPanel) oldContentPane);
            }
        });
    }
}
