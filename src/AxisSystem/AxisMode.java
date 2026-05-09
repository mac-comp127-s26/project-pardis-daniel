package AxisSystem;
import BrushesAndSlider.Brush;
import BrushesAndSlider.PaintSettingsView;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

/**
 * Interface for classes that determine reflection modes.
 */
public abstract class AxisMode {
    public abstract void applyReflections(Brush brush,
                            PaintSettingsView settings,
                            GraphicsGroup layer,
                            double centerX,
                            double centerY,
                            double dx,
                            double dy);
    
    public abstract String getName();

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
