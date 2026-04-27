import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * Single graphics group that acts as a painting surface
 * divides in four quadrants for vertical and horizontal reflection. 
 */
public class MandalaLayer {

    private final GraphicsGroup layer;
    private final double centerX;
    private final double centerY;

    /***
     * 
     * @param layer
     * @param centerX
     * @param centerY
     */
    public MandalaLayer(GraphicsGroup layer, double centerX, double centerY) {
        this.layer = layer;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void applyBrush(Brush brush, PaintSettingsView paintSettingsView, Point canvasPoint) {
        double dx = canvasPoint.getX() - centerX;
        double dy = canvasPoint.getY() - centerY;

        applyBrushReflection(brush, paintSettingsView, dx, dy);
        applyBrushReflection(brush, paintSettingsView, dx, -dy);
        applyBrushReflection(brush, paintSettingsView, -dx, dy);
        applyBrushReflection(brush, paintSettingsView, -dx, -dy);
    }

    private void applyBrushReflection(Brush brush, PaintSettingsView paintSettingsView, double dx, double dy) {
        brush.apply(layer, paintSettingsView, new Point(centerX + dx, centerY + dy));
    }

    public GraphicsGroup getLayer() {
        return layer;
    }

}
