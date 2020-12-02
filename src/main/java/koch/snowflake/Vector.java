package koch.snowflake;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

import static java.lang.Math.*;
import static java.math.MathContext.DECIMAL128;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_UP;

@ToString
@EqualsAndHashCode
public class Vector {

    public static final BigDecimal COS_60_DEGREE = BigDecimal.valueOf(cos(toRadians(60.0d)));
    public static final BigDecimal SIN_60_DEGREE = BigDecimal.valueOf(sin(toRadians(60.0d)));

    private final BigDecimal x, y;

    public Vector(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public Vector scaleToOneThird() {
        BigDecimal newX = this.x.divide(new BigDecimal("3"), DECIMAL128);
        BigDecimal newY = this.y.divide(new BigDecimal("3"), DECIMAL128);
        return new Vector(newX, newY);
    }

    public Vector rotateBySixtyDegree() {
        return rotateByDegree(-60.00d);
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

    public Vector rotateByDegree(double degreeOfRotation) {
        double radians = toRadians(degreeOfRotation);
        BigDecimal cos = new BigDecimal(cos(radians), DECIMAL128).setScale(10, HALF_UP);
        BigDecimal sin = new BigDecimal(sin(radians), DECIMAL128).setScale(10, HALF_UP);

        BigDecimal newX = x.multiply(cos).subtract(y.multiply(sin));
        BigDecimal newY = x.multiply(sin).add(y.multiply(cos));
        return new Vector(newX, newY);
    }
}
