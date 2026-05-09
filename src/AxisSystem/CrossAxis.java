package AxisSystem;
import BrushesAndSlider.Brush;
import BrushesAndSlider.PaintSettingsView;
import edu.macalester.graphics.GraphicsGroup;

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
