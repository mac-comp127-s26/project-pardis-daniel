package BrushesAndColor;
import java.util.Iterator;
import java.awt.Color;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class StrokeBucket implements Brush {
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
                    ellipse.setStrokeColor(color);
                } else if (obj instanceof Rectangle rectangle) {
                    rectangle.setStrokeColor(color);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Stroke Bucket";
    }

    @Override
    public String getImagePath() {
        return "brushes/strokeBucket.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(145, 445); 
    }
}