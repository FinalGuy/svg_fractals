package io.torbin.fractals;

import io.torbin.fractals.turtle.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.torbin.fractals.turtle.RotationAngle.SIXTY_DEGREE;

public class App {

    private static final int ITERATIONS = 5;

    public static void main(String[] args) throws Exception {
        drawSnowflake();
        drawPentaflake();
    }

    private static void drawSnowflake() throws Exception {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            drawSnowflakeInteration(iteration);
        }
    }

    private static void drawPentaflake() throws Exception {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            drawPentaflakeIteration(iteration);
        }
    }

    private static void drawSnowflakeInteration(int iteration) throws Exception {
        File output = new File("snowflake_iteration_" + iteration + ".svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "250");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("500"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = KochSnowflakeLSystem.iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, SIXTY_DEGREE);
    }

    private static void drawPentaflakeIteration(int iteration) throws Exception {
        File output = new File("pentaflake_iteration_" + iteration + ".svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "350");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("400"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = KochPentaflakeLSystem.iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, new RotationAngle(72));
    }
}    