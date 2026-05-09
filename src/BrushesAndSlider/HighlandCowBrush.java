package BrushesAndSlider;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public class HighlandCowBrush implements Brush {

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