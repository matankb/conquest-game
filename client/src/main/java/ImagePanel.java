import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private Image background;

    public ImagePanel(Image background) {
        this.background = background;
    }

    public ImagePanel(String path) {
        try {
            this.background = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Failed to load image `" + path + "`");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}