package brushesAndSlider;

import java.awt.Color;

/**
 * @author Daniel Aguilar
 * Describes the adjustable settings that can apply to a brush: color and
 * radius. It is everytime a brush is applied and based on the data store in a PaintSettingsView object.
 * Acknowledgements: Replicated from painter assignment materials for COMP127 course at Macalester College.
 */
public class BrushOptions {
    private Color color;
    private int radius;

    /**
     * Creates new BrushOptions
     * 
     * @param color  Color selected
     * @param radius Brusn size in pixels.
     */
    BrushOptions(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public int getRadius() {
        return radius;
    }
}
