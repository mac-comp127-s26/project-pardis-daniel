import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsGroup;

public class MandalaCanvas {
    private final CanvasWindow window;
    private final GraphicsGroup group;
    private final double width;
    private final double height;

    public MandalaCanvas(CanvasWindow window) {
        this.window = window;
        this.group = null;
        this.width = window.getWidth();
        this.height = window.getHeight();
    }

    
    public MandalaCanvas(GraphicsGroup group, double width, double height) {
        this.window = null;
        this.group = group;
        this.width = width;
        this.height = height;
    }

    public void add(GraphicsObject object) {
        if (window != null) {
            window.add(object);
        } else {
            group.add(object);
        }
    }

    public void add(GraphicsObject object, double x, double y) {
        if (window != null) {
            window.add(object, x, y);
        } else {
            object.setPosition(x, y);
            group.add(object);
        }
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public CanvasWindow getWindow() {
        return window;
    }
}
