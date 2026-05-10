import axisSystem.AxisMode;

/**
 * @author Daniel Aguilar
 * Holds logical state of a mandala drawing surface,
 * storing dimensions and current axis mode. 
 */
public class MandalaCanvas {
    private final double width;
    private final double height;
    private AxisMode axisMode;
    
    /**
     * Creates a MandalaCanvas with the fiven dimensions and axis mode
     * @param width
     * @param height
     * @param axisMode
     */
    public MandalaCanvas(double width, double height, AxisMode axisMode) {
        this.width = width;
        this.height = height;
        this.axisMode = axisMode;
    }

    /**
     * Updates the axis mode
     * @param axisMode
     */
    public void setAxisMode(AxisMode axisMode) {
        this.axisMode = axisMode;
    }

    public AxisMode getAxisMode() {
        return axisMode;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
