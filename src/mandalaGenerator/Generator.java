package mandalaGenerator;

import java.util.List;
import java.util.Random;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

/**
 * @author Pardis Roham
 * Uses the setup specifies to generate a mandala with concentric layers of geometric shapes.
 */
public class Generator {
    private static final int RIGHT_ANGLE = 90;
    private static final int MIN_CENTER_RING = 3;
    private static final int MAX_CENTER_RING = 7;
    private static final int INITIAL_CENTER_RING_SIZE = 35;

    private final MandalaSetup setup;
    private final Random rand;
    private final List<ShapeManager> shapes;

    /**
     * Creates a generator with the given setup.
     */
    public Generator(MandalaSetup setup) {
        this.setup = setup;
        this.rand = new Random(setup.getSeed());
        this.shapes = List.of(
            new CircleMaker(),
            new TriangleMaker(),
            new SquareMaker());
    }

    /**
     * Generates the complete mandala and puts it in a GraphicsGroup.
     */
    public GraphicsGroup generate() {
        GraphicsGroup mandala = new GraphicsGroup();

        addBorder(mandala);
        addCenterCircles(mandala);
        addLayers(mandala);

        return mandala;
    }

     /**
     * Adds layers of shapes arranged in circluar symmetry.
     */
    private void addLayers(GraphicsGroup mandala) {
        double center = setup.getCanvasSize() / 2;
        double layerSpacing = setup.getMaxRadius() / setup.getLayerCount();

        for (int layer = 1; layer <= setup.getLayerCount(); layer++) {
            double radius = layer * layerSpacing;
            double shapeSize = randomSizeForLayer(layer, layerSpacing);
            double strokeWidth = randomStrokeWidth();

            ShapeManager selectedShape = randomShape();

            for (int i = 0; i < setup.getSymmetryCount(); i++) {
                double angle = angleForIndex(i);
                double x = center + Math.cos(angle) * radius;
                double y = center + Math.sin(angle) * radius;

                GraphicsObject shape = selectedShape.create(x, y, shapeSize, Math.toDegrees(angle) + RIGHT_ANGLE, strokeWidth);

                mandala.add(shape);
            }
        }
    }

    /**
     * Adds a few circles at the center of the mandala.
     */
    private void addCenterCircles(GraphicsGroup mandala) {
        double center = setup.getCanvasSize() / 2;
        int centerRings = rand.nextInt(MIN_CENTER_RING, MAX_CENTER_RING);

        for (int i = 0; i < centerRings; i++) {
            double size = INITIAL_CENTER_RING_SIZE * i;
            double coordinates = center - size / 2;

            Ellipse circle = new Ellipse(coordinates, coordinates, size, size);

            circle.setStrokeWidth(randomStrokeWidth());

            mandala.add(circle);
        }
    }

    /**
     * Adds a circular border around the outer edge of the mandala.
     */
    private void addBorder(GraphicsGroup mandala) {
        double center = setup.getCanvasSize() / 2;
        double diameter = setup.getMaxRadius() * 2;
        double coordinates = center - diameter / 2;

        Ellipse border = new Ellipse(coordinates, coordinates, diameter, diameter);

        border.setStrokeWidth(randomStrokeWidth());

        mandala.add(border);
    }

    /*Helper methods */

    /**
     * Picks a random shape from the shapes list.
     */
    private ShapeManager randomShape() {
        int numberOfShapes = shapes.size();
        int randomIndex = rand.nextInt(numberOfShapes);
        ShapeManager selectedShape = shapes.get(randomIndex);

        return selectedShape;
    }

     /**
     * Calculates a random size for a shape in a given layer with some random variation.
     * Outer layers have proportionally larger sizes. 
     */
    private double randomSizeForLayer(int layer, double layerSpacing) {
        double base = layerSpacing * 0.35;
        double variation = rand.nextDouble() * base;
        double perLayerGrowth = layer * 3;

        return base + variation + perLayerGrowth;
    }

    /**
     * Generates a random stroke width.
     */
    private double randomStrokeWidth() {
        return 1 + rand.nextDouble();
    }

    /**
     * Calculates the angle for each position in a circular symmetry pattern.
     */
    private double angleForIndex(int index) {
        double fullCircle = 2 * Math.PI;
        int symmetryCount = setup.getSymmetryCount();
        double oneAngle = fullCircle / symmetryCount;
        double angle = index * oneAngle;

        return angle;
    }
}