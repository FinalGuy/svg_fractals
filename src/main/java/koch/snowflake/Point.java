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
        BigDecimal x = new BigDecimal(coordinates[0]);
        BigDecimal y = new BigDecimal(coordinates[1]);
        return new Point(x, y);
    }

    public String asCommaSeparatedCoordinates() {
        return x.toString() + ',' + y.toString();
    }

    public Vector vectorTo(Point location) {
        return new Vector(location.x.subtract(this.x), location.y.subtract(this.y));
    }

    public Point moveBy(Vector vector) {
        return new Point(this.x.add(vector.x()), this.y.add(vector.y()));
    }
}
