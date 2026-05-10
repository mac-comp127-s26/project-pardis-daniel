package brushesAndSlider;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Brush that stamps a Macalester College highland cow image onto a paint layer.
 */
public class HighlandCowBrush implements Brush {

    /**
     * Stamps cow image center at the location provided. 
     * The scale is re-scaled to 0.004 of the radius size provided. 
     */
    @Override
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();
        
        Image cow = new Image("brushes/highlandCow.png");
        cow.setScale(brushOptions.getRadius() * 0.004);
        cow.setCenter(location);
        paintLayer.add(cow);
    }

    @Override
    public String getName() {
        return "Highland Cow";
    }

    @Override
    public String getImagePath() {
        return "brushes/highlandCow.png";
    }

    @Override
    public Point getImagePosition() {
        return new Point(10, 445);
    }
}