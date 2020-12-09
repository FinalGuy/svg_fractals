package io.torbin.fractals;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

import static java.lang.Math.*;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_UP;

@ToString
@EqualsAndHashCode
public class Vector {

    public static final Vector ONE_UNIT_ALONG_X_AXIS = new Vector(BigDecimal.ONE, BigDecimal.ZERO);

    private final BigDecimal x, y;

    public Vector(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal x() {
        return x;
    }

    public BigDecimal y() {
        return y;
    }

    public BigDecimal length() {
        return x.pow(2).add(y.pow(2)).sqrt(DECIMAL128);
    }

    public Vector rotateBy120Degree() {
        return rotateByDegree(120);
    }

    public Vector rotateBy240Degree() {
        return rotateByDegree(240);
    }

    public Vector rotateByDegree(RotationAngle rotationAngle) {
        return rotateByDegree(rotationAngle.asDouble());
    }

    public Vector rotateByDegree(double degreeOfRotation) {
        double radians = toRadians(degreeOfRotation);
        BigDecimal cos = new BigDecimal(cos(radians), DECIMAL128).setScale(10, HALF_UP);
        BigDecimal sin = new BigDecimal(sin(radians), DECIMAL128).setScale(10, HALF_UP);

        BigDecimal newX = x.multiply(cos).subtract(y.multiply(sin));
        BigDecimal newY = x.multiply(sin).add(y.multiply(cos));
        return new Vector(newX, newY);
    }

    public Vector scaleBy(ScalingFactor scalingFactor) {
        BigDecimal newX = scalingFactor.applyTo(x);
        BigDecimal newY = scalingFactor.applyTo(y);
        return new Vector(newX, newY);
    }
}
