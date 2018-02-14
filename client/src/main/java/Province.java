import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Province extends MapElements {

    public Polygon provinceBounds;
    int[] xCoords;
    int[] yCoords;

    public Province(int[] xPoints, int[] yPoints, String name, int multiplier) {

        super();

        xPoints = xCoords;
        yPoints = yCoords;

        provinceBounds = new Polygon(xPoints, yPoints, xPoints.length);
        //this polygon is for the bounding box of the province, used for mouse hovering and for detecting clicks on the province


        panel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if (provinceBounds.contains(MouseInfo.getPointerInfo().getLocation())) {
                    //this code runs when the mouse clicks inside of the Province
                }
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i != xCoords.length; i++) {
            Point point = new Point(xCoords[i], yCoords[i]);
            points.add(point);
        }
        return points;
    }
}
