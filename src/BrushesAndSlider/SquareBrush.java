package brushesAndSlider;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Brush that draws holllows squares onto the paint layer.
 */
public class SquareBrush implements Brush {

    /**
     * Draws a transparent hollow square with a stroke color set based on the one stored in a paint settings. 
     * The side's size matches the radius set for a paint settings system through the size slider. 
     */
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        Rectangle circle = new Rectangle(location.getX(), location.getY(), brushOptions.getRadius(), brushOptions.getRadius());
        circle.setStrokeColor(brushOptions.getColor());
        circle.setStrokeWidth(1/2);
        circle.setCenter(location);
        paintLayer.add(circle);
    }

    @Override
        public String getName() {
        return "Square Paint";
    }

    @Override
    public String getImagePath() {
        return "brushes/square.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(145, 390);
    }
}