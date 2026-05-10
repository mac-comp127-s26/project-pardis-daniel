package brushesAndSlider;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

/**
 * Interactive slider component used inside a painting settings view. Displays draggable handles and
 * notifies the parent class if any value changes.
 */
public class ColorSlider extends GraphicsGroup {
    // Ratios to re-scale slider elements if necessary.
    private static final double TRACK_HEIGHT_RATIO = 0.35;
    private static final double HANDLE_RATIO = 0.90;
    private static final double LABEL_WIDTH_RATIO = 0.20;

    private final double trackX;
    private final double trackW;
    private final double trackY;
    private final double trackH;
    private final double handleDiam;
    private final double rowHeight;
    private final double minValue;
    private final double maxValue;
    private double currentValue;

    private final Rectangle track;
    private final Ellipse handle;
    private final PaintSettingsView parent;
    private boolean isDragging = false;

    public ColorSlider(String label, double minValue, double maxValue, double initialValue,
        double width, double height, CanvasWindow canvas, PaintSettingsView parent) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = initialValue;
        this.rowHeight = height;
        this.parent = parent;

        double labelWidth = width * LABEL_WIDTH_RATIO;
        trackX = labelWidth + width * 0.02;           // Tracks slighlty after the label
        trackW = width - trackX;                      // Tracks remaining width of the limits
        trackH = height * TRACK_HEIGHT_RATIO;
        trackY = (height - trackH) / 2.0;
        handleDiam = height * HANDLE_RATIO;

        // Initializes and positions the text label
        GraphicsText labelText = new GraphicsText(label + ":");
        labelText.setPosition(0, height / 2.0 + labelText.getHeight() * 0.3);
        labelText.setFillColor(Color.WHITE);
        add(labelText);

        // Initializes and positions the background track
        track = new Rectangle(trackX, trackY, trackW, trackH);
        track.setFillColor(Color.LIGHT_GRAY);
        track.setStroked(true);
        add(track);

        // Initializes circular handle
        handle = new Ellipse(0, 0, handleDiam, handleDiam);
        handle.setFillColor(Color.WHITE);
        handle.setStroked(false);
        add(handle);
        updateHandlePosition();  // Places handle on initial value

        // Checks if the user clicks in the handle to start dragging
        canvas.onMouseDown(event -> {
            Point local = toLocal(event.getPosition());
            if (handle.testHit(local.getX(), local.getY()) || track.testHit(local.getX(), local.getY())) {
                isDragging = true;
                setValueFromLocalX(local.getX());
            }
        });

        // Updates value while mouse click is held down as it moves
        canvas.onDrag(event -> {
            if (isDragging) {
                setValueFromLocalX(toLocal(event.getPosition()).getX());
            }
        });

        canvas.onMouseUp(event -> isDragging = false); // Stops movement when the mouse realeases the click
    }

    /**
     * Updates the slider value based on a local x coordinate.
     * 
     * @param localX
     */
    private void setValueFromLocalX(double localX) {
        // Converts pixel position to a ratio from 0 to 1.
        double ratio = Math.max(0, Math.min(1, (localX - trackX) / trackW));

        // Maps the 0-1 ratio to the pre-established range of values (0-256 for colors RGB channels)
        currentValue = minValue + ratio * (maxValue - minValue);

        updateHandlePosition();

        // Updates painting settings value based
        if (parent != null) {
            parent.updateFromSliders();
        }
    }

    /**
     * Repositions handle to match current value.
     */
    private void updateHandlePosition() {
        // Find where the current value stands in the 0-1 ratio
        double ratio = (currentValue - minValue) / (maxValue - minValue);

        // Tracks start to center ellipse handle
        double handleX = trackX + ratio * trackW - handleDiam / 2.0;
        double handleY = rowHeight / 2.0 - handleDiam / 2.0;

        handle.setPosition(handleX, handleY);
    }

    public void setValue(double value) {
        this.currentValue = Math.max(minValue, Math.min(maxValue, value));
        updateHandlePosition();
    }

    public double getValue() {
        return currentValue;
    }

    // Converts a global canvas point to a coordinate local to the slider.
    private Point toLocal(Point global) {
        return new Point(
            global.getX() - parent.getX() - getX(),
            global.getY() - parent.getY() - getY());
    }
}