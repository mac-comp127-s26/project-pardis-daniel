package brushesAndSlider;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.CanvasWindow;

import java.awt.Color;

/**
 * @author Daniel Aguilar
 * Displays UI panel showing current color and sliders for RGB channels and size. 
 * Acknowledgements: Based in painter assignment materials for COMP127 course at Macalester College.
 */
public class PaintSettingsView extends GraphicsGroup {
    private Color color;
    private int radius;
    private final Rectangle colorDisplay;
    private final ColorSlider redSlider;
    private final ColorSlider greenSlider;
    private final ColorSlider blueSlider;
    private final ColorSlider sizeSlider;

    private static final double DISPLAY_HEIGHT_RATIO = 0.25;
    private static final double GAP_RATIO = 0.02;
    private static final int MAX_BRUSH_SIZE = 100;

    /**
     * Creates the paint settings panel with color and size sliders.
     *
     * @param initialColor the starting brush color
     * @param initialSize  the starting brush radius in pixels
     * @param width        the width of the panel
     * @param height       the total height of the panel
     * @param canvas       the canvas to attach slider mouse listeners to
     */
    public PaintSettingsView(Color initialColor, int initialSize, double width, double height, CanvasWindow canvas) {
        double gapH = height * GAP_RATIO;
        double displayH = height * DISPLAY_HEIGHT_RATIO;
        double slidersH = height - displayH - gapH * 5;
        double sliderH = slidersH / 4.0;

        colorDisplay = new Rectangle(0, 0, width, displayH);
        add(colorDisplay);

        redSlider = new ColorSlider("R", 0, 255, initialColor.getRed(), width, sliderH, canvas, this);
        greenSlider = new ColorSlider("G", 0, 255, initialColor.getGreen(), width, sliderH, canvas, this);
        blueSlider = new ColorSlider("B", 0, 255, initialColor.getBlue(), width, sliderH, canvas, this);
        sizeSlider = new ColorSlider("Size", 1, MAX_BRUSH_SIZE, initialSize, width, sliderH, canvas, this);
        ColorSlider[] sliders = {redSlider, greenSlider, blueSlider, sizeSlider};

        double y = displayH + gapH;
        for (ColorSlider slider : sliders) {
            slider.setPosition(0, y);
            add(slider);
            y += sliderH + gapH;
        }

        this.radius = initialSize;
        setColor(initialColor);
    }

    /**
     * Called by sliders if handle moves.
     * Updates stored values and color display rectangle. 
     */
    public void updateFromSliders() {
        this.radius = (int) Math.round(sizeSlider.getValue());
        int r = (int) redSlider.getValue();
        int g = (int) greenSlider.getValue();
        int b = (int) blueSlider.getValue();
        this.color = new Color(r, g, b);
        colorDisplay.setFillColor(this.color);
    }

    /**
     * Sets brush color and updates all sliders to match. 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
        colorDisplay.setFillColor(color);
        redSlider.setValue(color.getRed());
        greenSlider.setValue(color.getGreen());
        blueSlider.setValue(color.getBlue());
    }

    /**
     * @return Current brush settings
     */
    public BrushOptions getBrushOptions() {
        return new BrushOptions(color, radius);
    }
}