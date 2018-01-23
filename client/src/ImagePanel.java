import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage background;

    public ImagePanel(String filePath) {
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
