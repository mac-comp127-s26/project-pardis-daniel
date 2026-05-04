import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

public class Eraser implements Brush {
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
    GraphicsObject objectInPlace = paintLayer.getElementAt(location);
    if (objectInPlace != null) {
        paintLayer.remove(objectInPlace);
        }
    }            

    @Override
    public String getName() {
        return "Eraser";
    }

    @Override
    public String getImagePath() {
        return "brushes/eraser.png"; 
    }

    @Override
    public Point getImagePosition() {
        return new Point(10, 360); 
    }
}