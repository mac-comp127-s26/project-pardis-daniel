import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.ui.Button;

import java.util.List;

import java.awt.Color;

public class MandalaMaker {
    private Brush currentBrush = new LineBrush();

    private final MandalaCanvas surface;
    private final MandalaLayer mandalaLayer;

    private final PaintSettingsView paintSettingsView;
    private final List<Brush> availableBrushes = List.of(new Eraser(),
                                                        new LineBrush());

    public MandalaMaker() {
        this(new CanvasWindow("Mandala Maker", 800, 800));
        }

    public MandalaMaker(CanvasWindow window) {
        this(new MandalaCanvas(window));
    }

    public MandalaMaker(GraphicsGroup group, double width, double height) {
        this(new MandalaCanvas(group, width, height));
    }

    private MandalaMaker(MandalaCanvas surface) {
        this.surface = surface;

        GraphicsGroup paintGroup = new GraphicsGroup();
        surface.add(paintGroup);

        double cx = surface.getWidth() / 2.0;
        double cy = surface.getHeight() / 2.0;
        mandalaLayer = new MandalaLayer(paintGroup, cx, cy);

        paintSettingsView = new PaintSettingsView(Color.BLUE, 60);
        surface.add(paintSettingsView,
            10 - paintSettingsView.getBounds().getMinX(), 10);

        double y = 300;
        for (Brush brush : availableBrushes) {
            addBrushButton(brush, y);
            y += 50;
        }

        if (surface.getWindow() != null) {
            surface.getWindow().onMouseDown(event -> paint(event.getPosition()));
            surface.getWindow().onDrag(event -> paint(event.getPosition()));
        }
    }

    private void paint(Point location) {
        mandalaLayer.applyBrush(currentBrush, paintSettingsView, location);
    }

    private void addBrushButton(Brush brush, double y) {
        Button button = new Button(brush.getName());
        button.setPosition(10, y);
        surface.add(button);
        button.onClick(() -> currentBrush = brush);
    }

    public static void main(String[] args) {
        new MandalaMaker();
    }
}
