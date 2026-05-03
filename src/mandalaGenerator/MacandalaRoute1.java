package mandalaGenerator;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

/**
 * Takes an input number and generates a corresponding mandala.
 */
public class MacandalaRoute1 {
    private static final int CANVAS_SIZE = 800;

    public static void main(String[] args) {
        int inputNumber = 365;

        CanvasWindow canvas = new CanvasWindow("Mandala Generator", CANVAS_SIZE, CANVAS_SIZE);
        MandalaSetup setup = new MandalaSetup(inputNumber, CANVAS_SIZE);
        Generator generator = new Generator(setup);

        GraphicsGroup mandala = generator.generate();
        canvas.add(mandala);
        canvas.draw();
    }
}

