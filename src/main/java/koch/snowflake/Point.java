package koch.snowflake;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
public class Point {

    private final BigDecimal x;
    private final BigDecimal y;

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public static Point fromCommaSeparatedCoordinates(String commaSeparatedCoordinates) {
        String[] coordinates = commaSeparatedCoordinates.split(",");
        return fromCoordinates(coordinates[0], coordinates[1]);
    }

    public static Point fromCoordinates(String x, String y) {
        return new Point(new BigDecimal(x), new BigDecimal(y));
    }

    public String asCommaSeparatedCoordinates() {
        return x.toString() + ',' + y.toString();
    }

    public Vector vectorTo(Point other) {
        return new Vector(other.x.subtract(this.x), other.y.subtract(this.y));
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
