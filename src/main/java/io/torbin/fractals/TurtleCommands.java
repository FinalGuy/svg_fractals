package io.torbin.fractals;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class TurtleCommands {

    private final String commandsAsString;

    public TurtleCommands(String commandsAsString) {
        this.commandsAsString = commandsAsString;
    }

    public void applyTo(Turtle turtle, Point initialPosition, Vector initialDirection, RotationAngle rotationAngle) throws Exception {
        turtle.start(initialPosition, initialDirection, rotationAngle);
        for (char c : commandsAsString.toCharArray()) {
            switch (c) {
                case 'F' -> turtle.moveForward();
                case '-' -> turtle.rotateLeft();
                case '+' -> turtle.rotateRight();
                default -> throw new IllegalArgumentException("Unknown command symbol: " + c);
            }
        }
        turtle.end();
    }

}
