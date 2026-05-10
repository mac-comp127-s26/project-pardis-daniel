package mandalaGenerator;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;

/**
 * @author Pardis Roham
 * Creates circles in the mandala.
 */
public class TriangleMaker implements ShapeManager {
    public GraphicsObject create(double centerX, double centerY, double size, double angle, double strokeWidth) {
        Path triangle = Path.makeTriangle(centerX, centerY - size, centerX - size, centerY + size, centerX + size,
            centerY + size);

        triangle.setStrokeWidth(strokeWidth);
        triangle.setRotation(angle);

        return triangle;
    }
}