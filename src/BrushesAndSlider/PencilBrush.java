package brushesAndSlider;

import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

/**
 * @author Daniel Aguilar
 * Brush that draws multiple color-filled circles and ellipses
 * of random sizes and opacities onto the paint layer to simulate a pencil.
 */
public class PencilBrush implements Brush {
    private static final int DOT_COUNT = 5; // Number of dots per brush application
    private static final double JITTER_RATIO = 0.4; // Scattering ratio as a fraction of the brush radius
    private static final double MIN_DOT_RATIO = 0.15; 
    private static final double MAX_DOT_RATIO = 0.35;
    private static final double MIN_OPACITY = 0.2;
    private static final double MAX_OPACITY = 0.85;

    private final Random rand = new Random();

    /**
     * Scatters small semi-transparent dots to create a pencil texture.
     */
    @Override
    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location) {
        BrushOptions options = paintSettingsView.getBrushOptions();
        double radius = options.getRadius();
        Color color = options.getColor();

        for (int i = 0; i < DOT_COUNT; i++) {
            double jitter = radius * JITTER_RATIO;  // Maximum distance a point apper from the brush application area
            double x = location.getX() + (rand.nextDouble() * 2 - 1) * jitter;
            double y = location.getY() + (rand.nextDouble() * 2 - 1) * jitter;

            // Randomizes size and opacity of dot within bounds stored as class variables
            double dotRadius = radius * (MIN_DOT_RATIO + rand.nextDouble() * (MAX_DOT_RATIO - MIN_DOT_RATIO));
            double opacity = MIN_OPACITY + rand.nextDouble() * (MAX_OPACITY - MIN_OPACITY);

            GraphicsObject dot = PaintUtils.createFuzzyDot(color, (float) dotRadius, opacity);
            dot.setCenter(new Point(x, y));
            paintLayer.add(dot);
        }
    }

    @Override
    public String getName() {
        return "Pencil";
    }

    @Override
    public String getImagePath() {
        return "brushes/pencil.png";
    }

    @Override
    public Point getImagePosition() {
        return new Point(145, 320); 
    }
}