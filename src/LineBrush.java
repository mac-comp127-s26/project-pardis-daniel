import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class LineBrush implements Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        GraphicsObject dot = (PaintUtils.createFuzzyDot(brushOptions.getColor(), brushOptions.getRadius(), 0.2f));
        dot.setCenter(location);
        paintLayer.add(dot);
    }

        public String getName() {
        return "Spray Paint";
    }

}