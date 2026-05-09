package brushesAndSlider;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;

/**
 * @author Daniel Aguilar
 * Interface that represent a brush tool that can be applied to a paint layer.
 * Acknowledgements: Based in painter assignment materials for COMP127 course at Macalester College.
 */
public interface Brush {

    public void apply(GraphicsGroup paintLayer, PaintSettingsView paintSettingsView, Point location);

    public String getName();

    /**
     * @return Image file path for the toolbar
     */
    String getImagePath();

    /**
     * @return Position of the the brush icon in the canvas.
     */
    Point getImagePosition();

}
