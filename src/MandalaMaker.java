import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.ui.Button;

import java.util.List;

import java.awt.Color;

public class MandalaMaker {
    private Brush currentBrush = new LineBrush();
    private CanvasWindow canvas;
    private GraphicsGroup paintLayer;

    private final PaintSettingsView paintSettingsView;
    private final List<Brush> availableBrushes = List.of(new Eraser(),
                                                        new LineBrush());

    public MandalaMaker() {
        canvas = new CanvasWindow("Painter", 900, 800);

        paintLayer = new GraphicsGroup();
        canvas.add(paintLayer);

        paintSettingsView = new PaintSettingsView(Color.BLUE, 60);
        canvas.add(paintSettingsView, 10 - paintSettingsView.getBounds().getMinX(), 10);

        canvas.onMouseDown(event -> paint(event.getPosition()));
        canvas.onDrag(event -> paint(event.getPosition()));

        double y = 300; 
        for (Brush brush: availableBrushes) {
            addBrushButton(brush, y);
            y += 50;
        }
    }

    private void paint(Point location) {
        currentBrush.apply(paintLayer, paintSettingsView, location);
    }

    private void addBrushButton(Brush brush, double y) {
        Button button = new Button(brush.getName());
        button.setPosition(10, y);
        canvas.add(button);
        button.onClick(() -> currentBrush = brush);
    }

    public static void main(String[] args) {
        new MandalaMaker();
    }
}
