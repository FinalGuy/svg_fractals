package koch.snowflake;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Math.*;

@ToString
@EqualsAndHashCode
public class Vector {

    public static final BigDecimal COS_60_DEGREE = BigDecimal.valueOf(cos(toRadians(60.0)));
    public static final BigDecimal SIN_60_DEGREE = BigDecimal.valueOf(sin(toRadians(60.0)));

    private BigDecimal x, y;

    public Vector(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public Vector scaleToOneThird() {
        BigDecimal newX = this.x.divide(new BigDecimal("3"), MathContext.DECIMAL128);
        BigDecimal newY = this.y.divide(new BigDecimal("3"), MathContext.DECIMAL128);
        return new Vector(newX, newY);
    }

    public Vector rotateBySixtyDegree() {
        BigDecimal newX = x.multiply(COS_60_DEGREE).subtract(y.multiply(SIN_60_DEGREE));
        BigDecimal newY = x.multiply(SIN_60_DEGREE).add(y.multiply(COS_60_DEGREE));
        return new Vector(newX, newY);
    }

    public BigDecimal x() {
        return x;
    }

    public BigDecimal y() {
        return y;
    }

    public BigDecimal length() {
        return x.pow(2).add(y.pow(2)).sqrt(MathContext.DECIMAL128);
    }

    public Vector rotateBy120Degree() {
        return rotateBySixtyDegree().rotateBySixtyDegree();
    }

    public Vector rotateBy240Degree() {
        return rotateBy120Degree().rotateBy120Degree();
    }
}
