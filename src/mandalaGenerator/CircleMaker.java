package mandalaGenerator;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

/**
 * Creates circles in the mandala.
 */
public class CircleMaker implements ShapeManager {
    public GraphicsObject create(double centerX, double centerY, double size, double angle, double strokeWidth) {
        Ellipse circle = new Ellipse(centerX - (size / 2), centerY - (size / 2), size, size);

        circle.setStrokeWidth(strokeWidth);

        return circle;
    }
}