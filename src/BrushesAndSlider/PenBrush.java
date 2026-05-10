package brushesAndSlider;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Brush that color-filled circles onto the paint layer to simulate a pen.
 */
public class PenBrush implements Brush {

    /**
     * Draws a circle with fill and stroke colors based on the one stored in a paint settings. 
     * The radius of the cirlce also matches the radius set for a paint settings system through the size slider. 
     */
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