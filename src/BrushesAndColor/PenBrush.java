package BrushesAndColor;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class PenBrush implements Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        Ellipse circle = new Ellipse(location.getX(), location.getY(), brushOptions.getRadius(), brushOptions.getRadius());
        circle.setFillColor(brushOptions.getColor());
        circle.setStrokeColor(brushOptions.getColor());
        circle.setCenter(location);
        paintLayer.add(circle);
    }

    @Override
        public String getName() {
        return "Pen";
    }

    @Override
    public String getImagePath() {
        return "brushes/pen.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(75, 320);
    }

}