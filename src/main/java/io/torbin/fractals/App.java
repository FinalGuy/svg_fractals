package io.torbin.fractals;

import io.torbin.fractals.svg.SvgTurtle;

import java.io.File;
import java.io.FileWriter;

import static io.torbin.fractals.RotationAngle.SIXTY_DEGREE;

public class App {

    private static final int ITERATIONS = 7;

    public static void main(String[] args) throws Exception {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            drawSnowflake(iteration);
        }
    }

    private static void drawSnowflake(int iteration) throws Exception {
        File output = new File("snowflake_iteration_" + iteration + ".svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "250");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("500"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = KochSnowflakeLSystem.iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, SIXTY_DEGREE);
    }
}    