package brushesAndSlider;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class SprayBrush implements Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        GraphicsObject dot = (PaintUtils.createFuzzyDot(brushOptions.getColor(), brushOptions.getRadius(), 0.2f));
        dot.setCenter(location);
        paintLayer.add(dot);
    }

    @Override
        public String getName() {
        return "Spray Paint";
    }

    @Override
    public String getImagePath() {
        return "brushes/sprayPaint.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(10, 320);
    }

}