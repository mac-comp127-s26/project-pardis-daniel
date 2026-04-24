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

        public String getName() {
        return "Eraser";
    }
}