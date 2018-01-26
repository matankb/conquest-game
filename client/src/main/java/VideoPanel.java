import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoPanel extends JPanel {

    /*
    INCOMPLETE
    This class is to create a custom JPanel with a video looped in the background.
    This would be mainly for the login screen, but we could make a second constructor for images
    for use in the main game itself.
     */

    private BufferedImage background;

    public VideoPanel(String filePath) {
        try {
            background = ImageIO.read(new File(filePath));
            paintComponents(getGraphics());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(background, 0, 0, null);
    }
}
