package io.torbin.fractals.turtle;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class TurtleCommands {

    public static final char FORWARD = 'F';
    public static final char ROTATE_LEFT = '-';
    public static final char ROTATE_RIGHT = '+';

    private final String commandsAsString;

    public TurtleCommands(String commandsAsString) {
        this.commandsAsString = commandsAsString;
    }

    public void applyTo(Turtle turtle, Point initialPosition, Vector initialDirection, RotationAngle rotationAngle) throws Exception {
        turtle.start(initialPosition, initialDirection, rotationAngle);
        for (char c : commandsAsString.toCharArray()) {
            switch (c) {
                case FORWARD -> turtle.moveForward();
                case ROTATE_LEFT -> turtle.rotateLeft();
                case ROTATE_RIGHT -> turtle.rotateRight();
                default -> throw new IllegalArgumentException("Unknown command symbol: " + c);
            }
        }
        turtle.end();
    }

}
