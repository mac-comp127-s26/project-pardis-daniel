import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;
import mandalaGenerator.Generator;
import mandalaGenerator.MandalaSetup;

/**
 * Screen for generating a mandala.
 * Displays an input field for the user to enter a number,
 * then generates and displays the corresponding mandala.
 */
public class InputScreen {
    private final CanvasWindow canvas;
    private static final int MANDALA_CANVAS_SIZE = 800;
    private static final Point POS_SAVE = new Point(900, 710);
    private static final Point POS_HOME   = new Point(900, 760);

    /**
     * Creates a mandala screen and displays the input UI.
     *
     * @param canvas the canvas window to display on
     */
    public InputScreen(CanvasWindow canvas) {
        this.canvas = canvas;

        showUI();
    }

    /**
     * Displays the input UI with a text field and button.
     */
    private void showUI() {

        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        Button textButton = new Button("Please enter your birthday as an integer. For example, November 15, 2005 would be 11152005");
        textButton.setPosition(centerX - 300, centerY - 50);

        TextField input = new TextField();
        input.setPosition(centerX - 50 , centerY);

        Button generateButton = new Button("Generate");
        generateButton.setPosition(centerX - 43, centerY + 50);

        generateButton.onClick(() -> {
            String inputText = input.getText();
            try {
                int inputNumber = Integer.parseInt(inputText.trim());
                generateMandala(inputNumber);
            } catch (NumberFormatException e) {
                System.out.println("Please enter your birthday as an integer.");
            }
        });

        canvas.add(input);
        canvas.add(textButton);
        canvas.add(generateButton);
        canvas.draw();
    }

    /**
     * Generates and displays the mandala for the given input number.
     */
    private void generateMandala(int inputNumber) {
        canvas.removeAll();

        MandalaSetup setup = new MandalaSetup(inputNumber,MANDALA_CANVAS_SIZE);
        Generator generator = new Generator(setup);

        GraphicsGroup mandala = generator.generate();
        canvas.add(mandala);
        addSaveButton(POS_SAVE);
        addHomeButton(POS_HOME);
    }


    //Helper methods
    private void addSaveButton(Point position) {
        Button saveButton = new Button("Save Mandala");
        saveButton.setPosition(position);
        canvas.add(saveButton);
        saveButton.onClick(() -> saveMandala());
    }

    private void saveMandala() {
        new Thread(() -> {
            canvas.screenShot("mandala.png");
        }).start();
    }

    private void addHomeButton(Point position) {
        Button homeButton = new Button("Home");
        homeButton.setPosition(position);
        canvas.add(homeButton);
        homeButton.onClick(() -> {
            canvas.removeAll();
            new MainScreen(canvas);
        });
    }
}
