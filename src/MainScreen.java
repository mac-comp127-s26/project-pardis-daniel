import AxisSystem.CrossAxis;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

public class MainScreen {
    private final CanvasWindow window;
    private final GraphicsGroup group;

    public MainScreen(CanvasWindow window) {
        this.window = window;
        this.group = new GraphicsGroup();

        Image background = new Image("background/macandalaBackground.png");
        background.setMaxWidth(window.getWidth());
        background.setPosition(0, 0);
        group.add(background);

        Button createButton = new Button("Create your own");
        createButton.setPosition(
            window.getWidth() / 2.0 - 120,
            window.getHeight() - 120
        );
        group.add(createButton);

        Button autoButton = new Button("Auto-generate and color");
        autoButton.setPosition(
            window.getWidth() / 2.0 + 20,
            window.getHeight() - 120
        );
        group.add(autoButton);

        createButton.onClick(() -> {
            window.removeAll();
            MandalaCanvas canvas = new MandalaCanvas(window.getWidth(), window.getHeight(), new CrossAxis());
            new MandalaMaker(window, canvas);
        });

        autoButton.onClick(() -> {
            // to-do
        });

        window.add(group);
    }

    public void show() {
        window.add(group);
    }

    public void hide() {
        window.remove(group);
    }
}