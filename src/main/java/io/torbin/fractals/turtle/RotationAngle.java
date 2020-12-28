package io.torbin.fractals.turtle;

public class RotationAngle {

    public static final RotationAngle SIXTY_DEGREE = new RotationAngle(60d);

    private double angle;

    public RotationAngle(double angle) {
        this.angle = angle;
    }

    public double asDouble() {
        return angle;
    }

    public RotationAngle negate() {
        return new RotationAngle(-1d * angle);
    }
}
