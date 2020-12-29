package io.torbin.fractals;

import io.torbin.fractals.turtle.*;

import java.io.File;
import java.io.FileWriter;

import static io.torbin.fractals.turtle.RotationAngle.SIXTY_DEGREE;

public class Fractals {

    private static final String HELP_MESSAGE = """
            Usage: Fractals <type> <number_of_iterations>
                        
            Where <type> can be one of the following options:
             - snow             : Creating a classic Koch snowflake 
             - penta            : Starting with a pentagram whose spikes fork iteratively
             - penta_inverted   : Starting with a pentagram whose spikes fork iteratively, BUT in inverted direction
                                  creating a nice self similar star decor.
             
            The <number_of_iterations> is quite self explanatory. For most types iterations above 5 can be quite 
            time and memory consuming.
            
            Example: `Fractals snow 4`
            """;


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Missing parameter.\n");
            System.out.println(HELP_MESSAGE);
            return;
        }
        String type = args[0];
        int iterations = Integer.parseInt(args[1]);
        switch (type) {
            case "snow" -> drawSnowflakeInteration(iterations);
            case "penta" -> drawPentaflakeIteration(iterations);
            case "penta_inverted" -> drawPentaflakeInvertedIteration(iterations);
            default -> System.out.println("The type you chose is unknown. Please look at the usage description:\n" + HELP_MESSAGE);
        }
    }

    private static void drawSnowflakeInteration(int iteration) throws Exception {
        File output = new File("snowflake_" + iteration + "_iterations.svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "650");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("500"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = new KochSnowflakeLSystem().iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, SIXTY_DEGREE);
    }

    private static void drawPentaflakeIteration(int iteration) throws Exception {
        File output = new File("pentaflake_" + iteration + "_iteration.svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "350");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("400"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = new KochPentaflakeLSystem().iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, new RotationAngle(72));
    }

    private static void drawPentaflakeInvertedIteration(int iteration) throws Exception {
        File output = new File("pentaflake_inverted_" + iteration + "_iteration.svg");
        Turtle turtle = new SvgTurtle(new FileWriter(output));
        Point initialPosition = Point.fromCoordinates("0", "350");
        Vector initialDirection = Vector.ONE_UNIT_ALONG_X_AXIS
                .scaleBy(new ScalingFactor("400"))
                .scaleBy(ScalingFactor.oneThirdPerIteration(iteration));
        TurtleCommands turtleCommands = new KochPentaflakeInvertedLSystem().iterate(iteration);
        turtleCommands.applyTo(turtle, initialPosition, initialDirection, new RotationAngle(72));
    }
}    