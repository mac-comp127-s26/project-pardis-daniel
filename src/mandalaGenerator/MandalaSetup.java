package mandalaGenerator;

import java.util.Random;

/**
 * @author Pardis Roham
 * Configuration for the mandala generator.
 * Derives parameters (seed, symmetry, layers, radius) from the input number.
 */
public class MandalaSetup {
     
    private static final int SYMMETRY_COUNT = 12;
    private static final long SEED_MULTIPLIER = 1000;
    private static final int MIN_LAYER = 4;
    private static final int MAX_LAYER = 10;

    private static final double MAX_RADIUS_RATIO = 0.45;

    // Fields
    private final int inputNumber;
    private final int canvasSize;
    private final long seed;
    private final int symmetryCount;
    private final int layerCount;
    private final double maxRadius;

    public MandalaSetup(int inputNumber, int canvasSize) {
        this.inputNumber = inputNumber;
        this.canvasSize = canvasSize;
        this.seed = inputNumber * SEED_MULTIPLIER;
        this.symmetryCount = SYMMETRY_COUNT;

        Random rand = new Random(seed);
        this.layerCount = rand.nextInt(MIN_LAYER, MAX_LAYER);
        this.maxRadius = canvasSize * MAX_RADIUS_RATIO;
    }

    public int getInputNumber() {
        return inputNumber;
    }

    public int getCanvasSize() {
        return canvasSize;
    }

    public long getSeed() {
        return seed;
    }

    public int getSymmetryCount() {
        return symmetryCount;
    }

    public int getLayerCount() {
        return layerCount;
    }

    public double getMaxRadius() {
        return maxRadius;
    }
}