package axisSystem;
import brushesAndSlider.Brush;
import brushesAndSlider.PaintSettingsView;
import edu.macalester.graphics.GraphicsGroup;

/**
 * @author Daniel Aguilar
 * Axis mode that extendsCrossAxis with additional reflections
 * at producing 12-way symmetry.
 */
public class DiagonalAxis extends CrossAxis {

    @Override
    public void applyReflections(Brush brush,
                            PaintSettingsView settings,
                            GraphicsGroup layer,
                            double centerX,
                            double centerY,
                            double dx,
                            double dy) {
        super.applyReflections(brush, settings, layer, centerX, centerY, dx, dy);
        
        double thirtyDegreesCos = Math.cos(Math.toRadians(22.5));
        double thirtyDegreesSin = Math.sin(Math.toRadians(22.5));
        double sixtyDegreesCos = Math.cos(Math.toRadians(67.5));
        double sixtyDegreesSin = Math.sin(Math.toRadians(67.5));

        applyBrushReflection(brush, settings, layer, centerX, centerY, dx * thirtyDegreesCos, dy * thirtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, dx * sixtyDegreesCos, dy * sixtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, dx * thirtyDegreesCos, -dy * thirtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, dx * sixtyDegreesCos, -dy * sixtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx * thirtyDegreesCos, dy * thirtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx * sixtyDegreesCos, dy * sixtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx  * thirtyDegreesCos, -dy * thirtyDegreesSin);
        applyBrushReflection(brush, settings, layer, centerX, centerY, -dx * sixtyDegreesCos, -dy * sixtyDegreesSin);
    }
    
    @Override
    public String getName() {
        return "Diagonal Reflection";
    }
}