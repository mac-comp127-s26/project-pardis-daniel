import mandalaGenerator.Generator;
import mandalaGenerator.MandalaSetup;
import brushesAndSlider.Brush;
import brushesAndSlider.Bucket;
import brushesAndSlider.PaintSettingsView;
import brushesAndSlider.StrokeBucket;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

import java.awt.Color;
import java.util.List;
import java.util.Iterator;

/**
 * @author Pardis Roham
 * Screen for generating a mandala. Displays an input field for the user to enter a number,
 * then generates and displays the corresponding mandala.
 */
public class InputScreen {
    private final CanvasWindow canvas;
    private static final int MANDALA_CANVAS_SIZE = 800;
    private static final Point POS_SAVE = new Point(900, 710);
    private static final Point POS_HOME = new Point(900, 760);

    private final PaintSettingsView paintSettingsView;
    private Brush currentBrush = new Bucket();

    /**
     * Creates a mandala screen and displays the input UI.
     *
     * @param canvas the canvas window to display on
     */
    public InputScreen(CanvasWindow canvas) {
        this.canvas = canvas;

        PaintSettingsView paintSettingsView = new PaintSettingsView(Color.BLUE, 10, 175, 400, canvas);
        this.paintSettingsView = paintSettingsView;

        Image background = new Image("background/secondaryBackground.png");
        background.setMaxWidth(canvas.getWidth());
        background.setPosition(0, 0);
        canvas.add(background);

        showUI();
    }

    /**
     * Displays the input UI with a text field and button.
     */
    private void showUI() {

        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;

        Button textButton = new Button(
            "Please enter your birthday as an integer. For example, November 15, 2005 would be 11152005");
        textButton.setPosition(centerX - 300, centerY - 50);

        TextField input = new TextField();
        input.setPosition(centerX - 50, centerY);

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

        Image background = new Image("background/secondaryBackground.png");
        background.setMaxWidth(canvas.getWidth());
        background.setPosition(0, 0);
        canvas.add(background);

        MandalaSetup setup = new MandalaSetup(inputNumber, MANDALA_CANVAS_SIZE);
        Generator generator = new Generator(setup);

        GraphicsGroup mandala = generator.generate();

        Rectangle clipBorder = new Rectangle(mandala.getX(), mandala.getY(), mandala.getWidth() * 1.04,
            mandala.getHeight() * 1.04);
        clipBorder.setFillColor(Color.WHITE);
        clipBorder.setStrokeColor(Color.LIGHT_GRAY);

        paintSettingsView.setPosition(clipBorder.getX() + clipBorder.getWidth() * 1.01, 10);

        canvas.add(clipBorder);
        canvas.add(mandala);
        canvas.add(paintSettingsView);

        addBucketControls(mandala);

        addSaveButton(POS_SAVE);
        addHomeButton(POS_HOME);
    }

    // Helper methods

    /**
     * Adds a button that screenshots the screen if clicked
     * Screenshots are saved in the main project folder
     * @param position
     */
    private void addSaveButton(Point position) {
        Button saveButton = new Button("Save Mandala");
        saveButton.setPosition(position);
        canvas.add(saveButton);
        saveButton.onClick(() -> saveMandala());
    }

    /**
     * Takes a screenshot of the window
     */
    private void saveMandala() {
        new Thread(() -> {
            canvas.screenShot("mandala.png");
        }).start();
    }

    /**
     * Adds a home button that returns the user to the main screen
     * @param position
     */
    private void addHomeButton(Point position) {
        Button homeButton = new Button("Home");
        homeButton.setPosition(position);
        canvas.add(homeButton);
        homeButton.onClick(() -> {
            canvas.removeAll();
            new HomeScreen(canvas);
        });
    }

    /**
     * Adds bucket tools and clearing button that returns the mandala generated to its original state
     * @param mandala Graphics group that holds the generated mandala
     */
    private void addBucketControls(GraphicsGroup mandala) {
        List<Brush> bucketBrushes = List.of(new Bucket(), new StrokeBucket());

        int i = 0;
        for (Brush brush : bucketBrushes) {
            Image img = new Image(brush.getImagePath());
            img.setMaxWidth(50);
            img.setPosition(900 + (i * 55), 600);
            i++;
            canvas.add(img);
            canvas.onMouseDown(event -> {
                if (img.testHit(event.getPosition().getX(), event.getPosition().getY())) {
                    currentBrush = brush;
                }
            });
        }

        canvas.onMouseDown(event -> {
            double ex = event.getPosition().getX();
            double ey = event.getPosition().getY();

            if (ex < mandala.getX() || ex > mandala.getX() + mandala.getWidth()) {
                return;
            }
            if (ey < mandala.getY() || ey > mandala.getY() + mandala.getHeight()) {
                return;
            }

            Point localPoint = new Point(ex - mandala.getX(), ey - mandala.getY());
            currentBrush.apply(mandala, paintSettingsView, localPoint);
        });

        Button clearFillButton = new Button("Clear All Shapes");
        clearFillButton.setPosition(900, 560);
        canvas.add(clearFillButton);
        clearFillButton.onClick(() -> {
            Iterator<GraphicsObject> iter = mandala.iterator();
            while (iter.hasNext()) {
                GraphicsObject obj = iter.next();
                if (obj instanceof Ellipse ellipse) {
                    ellipse.setFillColor(new Color(0, 0, 0, 0));
                    ellipse.setStrokeColor(Color.BLACK);
                } else if (obj instanceof Path path) {
                    path.setFillColor(new Color(0, 0, 0, 0));
                    path.setStrokeColor(Color.BLACK);
                } else if (obj instanceof Rectangle rectangle) {
                    rectangle.setFillColor(new Color(0, 0, 0, 0));
                    rectangle.setStrokeColor(Color.BLACK);
                }
            }
        });
    }
}