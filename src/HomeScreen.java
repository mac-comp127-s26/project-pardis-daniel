import axisSystem.CrossAxis;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.ui.Button;

/**
 * @author Daniel Aguilar
 * Creates the home screen of Macandala
 * Displays two buttons: one for the mandala maker, the other for the mandala generator
 */
public class HomeScreen {
    private final CanvasWindow window;
    private final GraphicsGroup group;

    /**
     * Creates and displays main screen elements
     * @param window aplication window
     */
    public HomeScreen(CanvasWindow window) {
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
            new DrawingScreen(window, canvas);
        });

        autoButton.onClick(() -> {
            window.removeAll();
            new InputScreen(window);
        });

        window.add(group);
    }

    /**
     * Adds the main screen graphic group to the window
     */
    public void show() {
        window.add(group);
    }

    /**
     * Removes the graphics group from the window
     */
    public void hide() {
        window.remove(group);
    }
}