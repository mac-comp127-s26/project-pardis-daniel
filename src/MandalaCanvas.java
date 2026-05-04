public class MandalaCanvas {
    private final double width;
    private final double height;
    private AxisMode axisMode;
    
    public MandalaCanvas(double width, double height, AxisMode axisMode) {
        this.width = width;
        this.height = height;
        this.axisMode = axisMode;
    }

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
