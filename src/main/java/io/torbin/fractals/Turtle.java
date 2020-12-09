package io.torbin.fractals;

public interface Turtle {

    void start(Point initialPosition, Vector initialDirection, RotationAngle rotationAngle) throws Exception;

    void moveForward() throws Exception;

    void rotateLeft() throws Exception;

    void rotateRight() throws Exception;

    void end() throws Exception;

}
