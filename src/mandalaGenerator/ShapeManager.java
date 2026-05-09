package mandalaGenerator;

import edu.macalester.graphics.GraphicsObject;

/**
 * @author Pardis Roham
 */
public interface ShapeManager {
    /**
     * Creates a shape at the given position and angle.
     *
     * @param centerX the x coordinate of the shape center
     * @param centerY the y coordinate of the shape center
     * @param size the size of the shape
     * @param angle the angle for orientation of the shape
     * @param strokeWidth the width of the outline stroke
     * @return a GraphicsObject representing the shape
     */
    GraphicsObject create(double centerX, double centerY, double size, double angle, double strokeWidth);
}