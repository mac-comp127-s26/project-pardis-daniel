package axisSystem;

import brushesAndSlider.Brush;
import brushesAndSlider.PaintSettingsView;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Interface for classes that determine reflection modes.
 * Each subclass defines a different symmetry pattern. 
 */
public abstract class AxisMode {

    /**
     * Applies the brush at all reflected positions defined by this axis mode.
     *
     * @param brush     the brush to apply
     * @param settings  the current paint settings
     * @param layer     the graphics group to paint into
     * @param centerX   the x coordinate of the canvas center
     * @param centerY   the y coordinate of the canvas center
     * @param dx        the horizontal offset from center of the original point
     * @param dy        the vertical offset from center of the original point
     */
    public abstract void applyReflections(Brush brush,
                            PaintSettingsView settings,
                            GraphicsGroup layer,
                            double centerX,
                            double centerY,
                            double dx,
                            double dy);
    
    /**
     * Returns names to display for the axis mode. 
     */
    public abstract String getName();

    /**
     * Applies the brush at a single reflected position offset from the center.
     *
     * @param brush     the brush to apply
     * @param settings  the current paint settings
     * @param layer     the graphics group to paint into
     * @param cx        the x coordinate of the canvas center
     * @param cy        the y coordinate of the canvas center
     * @param dx        the horizontal offset from center for this reflection
     * @param dy        the vertical offset from center for this reflection
     */
    protected void applyBrushReflection(Brush brush,
                                        PaintSettingsView settings,
                                        GraphicsGroup layer,
                                        double cx,
                                        double cy,
                                        double dx,
                                        double dy) {
        brush.apply(layer, settings, new Point(cx + dx, cy + dy));
    }
}