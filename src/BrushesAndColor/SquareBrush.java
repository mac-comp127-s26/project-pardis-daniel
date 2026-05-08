package BrushesAndColor;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class SquareBrush implements Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        Rectangle circle = new Rectangle(location.getX(), location.getY(), brushOptions.getRadius(), brushOptions.getRadius());
        circle.setStrokeColor(brushOptions.getColor());
        circle.setStrokeWidth(1/2);
        circle.setCenter(location);
        paintLayer.add(circle);
    }

    @Override
        public String getName() {
        return "Square Paint";
    }

    @Override
    public String getImagePath() {
        return "brushes/square.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(145, 390);
    }

}