package BrushesAndColor;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class CircleBrush implements Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        Ellipse circle = new Ellipse(location.getX(), location.getY(), brushOptions.getRadius() * 2, brushOptions.getRadius() * 2);
        circle.setStrokeColor(brushOptions.getColor());
        circle.setStrokeWidth(1/2);
        circle.setCenter(location);
        paintLayer.add(circle);
    }

    @Override
        public String getName() {
        return "Spray Paint";
    }

    @Override
    public String getImagePath() {
        return "brushes/circle.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(75, 390);
    }

}