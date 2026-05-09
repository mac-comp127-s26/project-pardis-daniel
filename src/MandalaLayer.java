import brushesAndSlider.Brush;
import brushesAndSlider.PaintSettingsView;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Single graphics group that acts as a the painting surface.
 * Divided into four quadrants for vertical and horizontal reflection. 
 */
public class MandalaLayer {

    private final GraphicsGroup layer;
    private final double centerX;
    private final double centerY;
    private MandalaCanvas mandalaCanvas;

    /***
     * Creates a MandalaLayer backed by the given graphics group.
     * @param layer
     * @param centerX
     * @param centerY
     * @param mandalaCanvas Canvas holding axis mode
     */
    public MandalaLayer(GraphicsGroup layer, double centerX, double centerY, MandalaCanvas mandalaCanvas) {
        this.layer = layer;
        this.centerX = centerX;
        this.centerY = centerY;
        this.mandalaCanvas = mandalaCanvas;
    }

    /**
     * Applies the given brush.
     * @param brush
     * @param paintSettingsView Current paint settings that store color and size (radius)
     * @param canvasPoint Point where the user clicked
     */
    public void applyBrush(Brush brush, PaintSettingsView paintSettingsView, Point canvasPoint) {
        double dx = canvasPoint.getX() - centerX;
        double dy = canvasPoint.getY() - centerY;
        mandalaCanvas.getAxisMode().applyReflections(brush, paintSettingsView, layer, centerX, centerY, dx, dy);
    }

    /**
     * @return Graphic groups used as the paint surface.
     */
    public GraphicsGroup getLayer() {
        return layer;
    }
}
