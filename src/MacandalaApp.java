import edu.macalester.graphics.CanvasWindow;

/**
 * Creates the Macandala app window and launches the main screen. 
 */
public class MacandalaApp {
    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("Macandala", 1024, 800);
        new HomeScreen(window);
    }
}