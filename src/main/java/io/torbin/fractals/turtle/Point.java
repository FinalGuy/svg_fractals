package io.torbin.fractals.turtle;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class Point {

    public static final Point ORIGIN = new Point(BigDecimal.ZERO, BigDecimal.ZERO);

    private final BigDecimal x;
    private final BigDecimal y;

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public static Point fromCoordinates(String x, String y) {
        return new Point(new BigDecimal(x), new BigDecimal(y));
    }

    public Point moveBy(Vector vector) {
        return new Point(this.x.add(vector.x()), this.y.add(vector.y()));
    }

    public BigDecimal x() {
        return x;
    }

    public BigDecimal y() {
        return y;
    }
}
