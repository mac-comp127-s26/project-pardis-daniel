import edu.macalester.graphics.CanvasWindow;

public class MacandalaApp {
    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("Macandala", 1024, 800);
        new MainScreen(window);
    }
}