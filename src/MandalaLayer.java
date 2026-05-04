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
    private MandalaCanvas mandalaCanvas;

    /***
     * 
     * @param layer
     * @param centerX
     * @param centerY
     */
    public MandalaLayer(GraphicsGroup layer, double centerX, double centerY, MandalaCanvas mandalaCanvas) {
        this.layer = layer;
        this.centerX = centerX;
        this.centerY = centerY;
        this.mandalaCanvas = mandalaCanvas;
    }

    public void applyBrush(Brush brush, PaintSettingsView paintSettingsView, Point canvasPoint) {
        double dx = canvasPoint.getX() - centerX;
        double dy = canvasPoint.getY() - centerY;
        mandalaCanvas.getAxisMode().applyReflections(brush, paintSettingsView, layer, centerX, centerY, dx, dy);
    }

    public GraphicsGroup getLayer() {
        return layer;
    }

}
