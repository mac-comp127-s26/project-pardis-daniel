package axisSystem;
import brushesAndSlider.Brush;
import brushesAndSlider.PaintSettingsView;
import edu.macalester.graphics.GraphicsGroup;

/**
 * @author Daniel Aguilar
 * Main axis in Macandala. Reflects brush strokes across
 * both the horizontal and vertical axes.
 */
public class CrossAxis extends AxisMode {

    @Override
    public void applyReflections(Brush brush,
                            PaintSettingsView settings,
                            GraphicsGroup layer,
                            double centerX,
                            double centerY,
                            double dx,
                            double dy) {
        applyBrushReflection(brush, settings, layer, centerX, centerY, dx, dy);
        applyBrushReflection(brush, settings, layer, centerX, centerY, dx, -dy);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx, dy);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx, -dy);
    }

    @Override
    public String getName() {
        return "Cross Reflection";
    }
}
