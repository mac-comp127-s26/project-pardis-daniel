package BrushesAndSlider;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;

public interface Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location);

    public String getName();

    String getImagePath();

    Point getImagePosition();

}
