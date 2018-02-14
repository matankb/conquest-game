import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

public class Map extends JComponent {

    private JPanel panel;

    /*
    * Map class is what displays the map in the main Game UI
    */

    public Map() {
        setDefaults();
    }
    public void setDefaults() {
        panel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //change image name when we have the final map image made
        panel = new ImagePanel("client/src/images/background_image_login"); //change to relative to either project file or bin directory
    }
}
