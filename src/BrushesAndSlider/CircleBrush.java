package brushesAndSlider;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Brush that draws holllows circles onto the paint layer.
 * Acknowledgements: Based in painter assignment materials for COMP127 course at Macalester College.
 */
public class CircleBrush implements Brush {

    /**
     * Draws a transparent hollow circle with a stroke color set based on the one stored in a paint
     * settings. The radius of the cirlce also matches the radius set for a paint settings system
     * through the size slider.
     */
    @Override
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        Ellipse circle = new Ellipse(location.getX(), location.getY(), brushOptions.getRadius() * 2,
            brushOptions.getRadius() * 2);
        circle.setStrokeColor(brushOptions.getColor());
        circle.setStrokeWidth(1 / 2);
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