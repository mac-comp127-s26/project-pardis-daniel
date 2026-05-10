package brushesAndSlider;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Path;

import java.util.Iterator;
import java.awt.Color;

/**
 * @author Daniel Aguilar
 * Brush that sets fill color of any shapes surrounding a given radius from the click location
 */
public class Bucket implements Brush {

    /**
     * Fills all shapes within the brush radius of the given location
     * with the current paint color
     */
    @Override
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        double radius = paintSettingsView.getBrushOptions().getRadius();
        Color color = paintSettingsView.getBrushOptions().getColor();

        Iterator<GraphicsObject> iter = paintLayer.iterator();
        while (iter.hasNext()) {
            GraphicsObject obj = iter.next();
            Point center = obj.getCenter();
            double dist = Math.hypot(center.getX() - location.getX(), center.getY() - location.getY());
            if (dist <= radius) {
                if (obj instanceof Ellipse ellipse) {
                    ellipse.setFillColor(color);
                } else if (obj instanceof Rectangle rectangle) {
                    rectangle.setFillColor(color);
                } else if (obj instanceof Path path) {
                    path.setFillColor(color);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bucket";
    }

    @Override
    public String getImagePath() {
        return "brushes/bucket.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(75, 445); 
    }
}