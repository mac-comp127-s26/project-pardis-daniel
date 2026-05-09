package brushesAndSlider;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class Eraser implements Brush {
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        double radius = paintSettingsView.getBrushOptions().getRadius();

        List<GraphicsObject> toRemove = new ArrayList<>();
        Iterator<GraphicsObject> iter = paintLayer.iterator();
        while (iter.hasNext()) {
            GraphicsObject obj = iter.next();
            Point center = obj.getCenter();
            double dist = Math.hypot(center.getX() - location.getX(), center.getY() - location.getY());
            if (dist <= radius) {
                toRemove.add(obj);
            }
        }
        for (GraphicsObject obj : toRemove) {
            paintLayer.remove(obj);
        }
    }


    @Override
    public String getName() {
        return "Eraser";
    }

    @Override
    public String getImagePath() {
        return "brushes/eraser.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(10, 390); 
    }
}