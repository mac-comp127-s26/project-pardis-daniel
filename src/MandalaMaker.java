import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;
import java.util.List;


public class MandalaMaker {
    private Brush currentBrush = new SprayBrush();

    private final CanvasWindow canvasWindow;
    private final MandalaCanvas surface;
    private final MandalaLayer mandalaLayer;
    private final PaintSettingsView paintSettingsView;
    private final List<Brush> availableBrushes = List.of(new Eraser(),
                                                        new SprayBrush(),
                                                        new HighlandCowBrush());

    private static final double TOOLS_PANEL_WIDTH  = 220;
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 800;

    private static final double PAINT_AREA_X = TOOLS_PANEL_WIDTH + 10;
    private static final double PAINT_AREA_WIDTH = WINDOW_WIDTH - PAINT_AREA_X - 10;
    private static final double PAINT_AREA_HEIGHT = WINDOW_HEIGHT - 20;

    private static final Point POS_CROSS = new Point(10, 500);
    private static final Point POS_MIRROR = new Point(10, 550);
    private static final Point POS_STAR = new Point(10, 600);
    private static final Point POS_SAVE = new Point(10, 660);
    private static final Point POS_CLEAR = new Point(10, 710);

    private MandalaMaker(CanvasWindow canvasWindow, MandalaCanvas surface) {
        this.canvasWindow = canvasWindow;
        this.surface = surface;

        Rectangle clipBorder = new Rectangle(PAINT_AREA_X, 10, PAINT_AREA_WIDTH, PAINT_AREA_HEIGHT);
        clipBorder.setFillColor(Color.WHITE);
        clipBorder.setStrokeColor(Color.LIGHT_GRAY);
        canvasWindow.add(clipBorder);

        GraphicsGroup paintGroup = new GraphicsGroup();
        paintGroup.setPosition(PAINT_AREA_X, 0);
        canvasWindow.add(paintGroup);

        double paintAreaWidth = surface.getWidth() - TOOLS_PANEL_WIDTH;
        double cx = PAINT_AREA_WIDTH / 2; 
        double cy = 10 + (PAINT_AREA_HEIGHT / 2.0);
        mandalaLayer = new MandalaLayer(paintGroup, cx, cy, surface);

        paintSettingsView = new PaintSettingsView(Color.BLUE, 10, 200, 300, canvasWindow);
        canvasWindow.add(paintSettingsView, 10, 10);

        for (Brush brush : availableBrushes) {
            addBrushImageButton(brush);
        }

        addAxisButton(new CrossAxis(),  POS_CROSS);
        addAxisButton(new MirrorAxis(), POS_MIRROR);
        addAxisButton(new DiagonalAxis(),   POS_STAR);
        addSaveButton(POS_SAVE);
        addClearButton(POS_CLEAR, paintGroup);

        canvasWindow.onMouseDown(event -> paint(event.getPosition()));
        canvasWindow.onDrag(event -> paint(event.getPosition()));
    }

    private void paint(Point location) {
        double lx = location.getX();
        double ly = location.getY();
        double radius = paintSettingsView.getBrushOptions().getRadius();

        if (lx < PAINT_AREA_X + radius || lx > PAINT_AREA_X + PAINT_AREA_WIDTH - radius) {
            return;
        }
        if (ly < 10 + radius || ly > 10 + PAINT_AREA_HEIGHT - radius) {
            return;
        }

        double localX = lx - PAINT_AREA_X;
        double localY = ly - 10;
        mandalaLayer.applyBrush(currentBrush, paintSettingsView, new Point(localX, localY));
    }

    private void addBrushImageButton(Brush brush) {
        Image img = new Image(brush.getImagePath());
        img.setMaxWidth(50);
        img.setPosition(brush.getImagePosition());
        canvasWindow.add(img);
        canvasWindow.onMouseDown(event -> {
            if (img.testHit(event.getPosition().getX(), event.getPosition().getY())) {
                currentBrush = brush;
            }
        });
    }

    private void addAxisButton(AxisMode mode, Point position) {
        Button button = new Button(mode.getName());
        button.setPosition(position);
        canvasWindow.add(button);
        button.onClick(() -> surface.setAxisMode(mode));
    }

    private void addSaveButton(Point position) {
        Button saveButton = new Button("Save Mandala");
        saveButton.setPosition(position);
        canvasWindow.add(saveButton);
        saveButton.onClick(() -> saveMandala());
    }

    private void saveMandala() {
        new Thread(() -> {
            canvasWindow.screenShot("mandala.png");
        }).start();
    }

    private void addClearButton(Point position, GraphicsGroup paintGroup) {
        Button clearButton = new Button("Clear Screen");
        clearButton.setPosition(position);
        canvasWindow.add(clearButton);
        clearButton.onClick(() -> {
            paintGroup.removeAll();
        });
}

    public static void main(String[] args) {
        CanvasWindow window  = new CanvasWindow("Mandala Maker", WINDOW_WIDTH, WINDOW_HEIGHT);
        MandalaCanvas canvas = new MandalaCanvas(window.getWidth(), window.getHeight(), new CrossAxis());
        new MandalaMaker(window, canvas);
    }
}
