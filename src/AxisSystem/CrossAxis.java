package AxisSystem;
import BrushesAndColor.Brush;
import BrushesAndColor.PaintSettingsView;
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
