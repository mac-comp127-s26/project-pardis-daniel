package mandalaGenerator;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;

public class SquareMaker implements ShapeManager {
    public GraphicsObject create(double centerX, double centerY, double size, double angle, double strokeWidth) {

        double halfSize = size / 2;
        double xLeft = centerX - halfSize;
        double xRight = centerX + halfSize;
        double yUp = centerY - halfSize;
        double yDown = centerY + halfSize;

        Path square = new Path(
            new Point(xLeft, yUp),
            new Point(xRight, yUp),
            new Point(xRight, yDown),
            new Point(xLeft, yDown));

        square.setStrokeWidth(strokeWidth);
        square.setRotation(angle);

        return square;
    }
}
