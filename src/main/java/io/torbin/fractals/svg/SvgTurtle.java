package io.torbin.fractals.svg;

import io.torbin.fractals.*;

import java.io.Writer;

public class SvgTurtle implements Turtle {

    private static final String SVG_HEADER = """
            <?xml version="1.0" encoding="UTF-8"?>
            <svg xmlns="http://www.w3.org/2000/svg"
                    xmlns:xlink="http://www.w3.org/1999/xlink"
                    version="1.1" baseProfile="full"
                    width="1000" height="1000">
                <title>Koch'sche Schneeflocke</title>
                <desc>...</desc>
                
            """;
    private static final String SVG_FOOTER = "</svg>\n";

    private final Writer fileWriter;

    private Point currentPosition;
    private Vector currentDirection;
    private RotationAngle rotationAngle;

    public SvgTurtle(Writer writer) {
        this.fileWriter = writer;
    }


    @Override
    public void start(Point initialPosition, Vector initialDirection, RotationAngle rotationAngle) throws Exception {
        this.currentPosition = initialPosition;
        this.currentDirection = initialDirection;
        this.rotationAngle = rotationAngle;
        fileWriter.append(SVG_HEADER).flush();
    }

    @Override
    public void moveForward() throws Exception {
        Point newPosition = currentPosition.moveBy(currentDirection);
        Line line = new Line(currentPosition, newPosition);
        fileWriter.append(line.asSvg()).append("\n").flush();
        this.currentPosition = newPosition;
    }

    @Override
    public void rotateLeft() {
        currentDirection = currentDirection.rotateByDegree(rotationAngle);
    }

    @Override
    public void rotateRight() {
        currentDirection = currentDirection.rotateByDegree(rotationAngle.negate());
    }

    @Override
    public void end() throws Exception {
        fileWriter.append(SVG_FOOTER);
        fileWriter.close();
    }

    public Vector currentDirection() {
        return currentDirection;
    }
}
