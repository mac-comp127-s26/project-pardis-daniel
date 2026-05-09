package AxisSystem;
import BrushesAndSlider.Brush;
import BrushesAndSlider.PaintSettingsView;
import edu.macalester.graphics.GraphicsGroup;

public class MirrorAxis extends CrossAxis {

    @Override
    public void applyReflections(Brush brush,
                            PaintSettingsView settings,
                            GraphicsGroup layer,
                            double centerX,
                            double centerY,
                            double dx,
                            double dy) {
        super.applyReflections(brush, settings, layer, centerX, centerY, dx, dy);

        applyBrushReflection(brush, settings, layer, centerX, centerY, dy, dx);
        applyBrushReflection(brush, settings, layer, centerX, centerY, dy, -dx);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dy, dx);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dy, -dx);
    }
    
    @Override
    public String getName() {
        return "Mirror Reflection";
    }
}
