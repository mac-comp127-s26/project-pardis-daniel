import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;

public class ColorSlider extends GraphicsGroup {
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
        trackX = labelWidth + width * 0.02;
        trackW = width - trackX;
        trackH = height * TRACK_HEIGHT_RATIO;
        trackY = (height - trackH) / 2.0;
        handleDiam = height * HANDLE_RATIO;

        GraphicsText labelText = new GraphicsText(label + ":");
        labelText.setPosition(0, height / 2.0 + labelText.getHeight() * 0.3);
        add(labelText);

        track = new Rectangle(trackX, trackY, trackW, trackH);
        track.setFillColor(Color.LIGHT_GRAY);
        track.setStroked(true);
        add(track);

        handle = new Ellipse(0, 0, handleDiam, handleDiam);
        handle.setFillColor(Color.DARK_GRAY);
        handle.setStroked(false);
        add(handle);

        updateHandlePosition();

        canvas.onMouseDown(event -> {
            Point local = toLocal(event.getPosition());
            if (handle.testHit(local.getX(), local.getY()) || track.testHit(local.getX(), local.getY())) {
                isDragging = true;
                setValueFromLocalX(local.getX());
            }
        });
        canvas.onDrag(event -> {
            if (isDragging) {
                setValueFromLocalX(toLocal(event.getPosition()).getX());
            }
        });
        canvas.onMouseUp(event -> isDragging = false);
    }

    private void setValueFromLocalX(double localX) {
        double ratio = Math.max(0, Math.min(1, (localX - trackX) / trackW));
        currentValue = minValue + ratio * (maxValue - minValue);
        updateHandlePosition();
        if (parent != null) {
            parent.updateFromSliders();
        }
    }

    private void updateHandlePosition() {
        double ratio = (currentValue - minValue) / (maxValue - minValue);
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

    private Point toLocal(Point global) {
        return new Point(global.getX() - getX(), global.getY() - getY());
    }
}