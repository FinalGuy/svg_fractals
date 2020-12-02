package koch.snowflake;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Triangle {

    private final Point top;
    private final Point bottomRight;
    private final Point bottomLeft;

    public Triangle(Point top, Point bottomRight, Point bottomLeft) {
        this.top = top;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    public static Triangle fromCoordinates(String coordinates) {
        String[] attributeEntries = coordinates.split(" ");
        Point[] points = new Point[3];
        for (int i = 0; i < attributeEntries.length; i++) {
            points[i] = Point.fromCommaSeparatedCoordinates(attributeEntries[i]);
        }
        return new Triangle(points[0], points[1], points[2]);
    }

    public static Triangle centeredAt(Point center) {
        Vector toTop = new Vector(BigDecimal.ZERO, new BigDecimal("-100"));
        Point top = center.moveBy(toTop);
        Point bottomRight = center.moveBy(toTop.rotateBy120Degree());
        Point bottomLeft = center.moveBy(toTop.rotateBy240Degree());
        return new Triangle(top, bottomRight, bottomLeft);
    }

    public String asSvgPolygon() {
        return String.format("<polygon points=\"%s %s %s\" />",
                top.asCommaSeparatedCoordinates(),
                bottomRight.asCommaSeparatedCoordinates(),
                bottomLeft.asCommaSeparatedCoordinates());
    }

    public List<Triangle> applyIteration() {
        List<Triangle> triangles = new ArrayList<>(3);
        triangles.add(createTriangleTopRight());
        triangles.add(createTriangleBottom());
        triangles.add(createTriangleTopLeft());
        return triangles;
    }

    private Triangle createTriangleTopRight() {
        Point newBottomLeft = top.moveBy(vectorFromTopToBottomRight().scaleToOneThird());
        Point newBottomRight = newBottomLeft.moveBy(vectorFromTopToBottomRight().scaleToOneThird());
        Point newTop = newBottomLeft.moveBy(vectorFromTopToBottomRight().scaleToOneThird().rotateBySixtyDegree());
        return new Triangle(newTop, newBottomRight, newBottomLeft);
    }

    private Triangle createTriangleBottom() {
        Point newBottomLeft = bottomRight.moveBy(vectorFromBottomRightToBottomLeft().scaleToOneThird());
        Point newBottomRight = newBottomLeft.moveBy(vectorFromBottomRightToBottomLeft().scaleToOneThird());
        Point newTop = newBottomLeft.moveBy(vectorFromBottomRightToBottomLeft().scaleToOneThird().rotateBySixtyDegree());
        return new Triangle(newTop, newBottomRight, newBottomLeft);
    }

    private Triangle createTriangleTopLeft() {
        Point newBottomLeft = bottomLeft.moveBy(vectorFromBottomLeftToTop().scaleToOneThird());
        Point newBottomRight = newBottomLeft.moveBy(vectorFromBottomLeftToTop().scaleToOneThird());
        Point newTop = newBottomLeft.moveBy(vectorFromBottomLeftToTop().scaleToOneThird().rotateBySixtyDegree());
        return new Triangle(newTop, newBottomRight, newBottomLeft);
    }

    public Vector vectorFromTopToBottomRight() {
        return top.vectorTo(bottomRight);
    }

    public Vector vectorFromBottomRightToBottomLeft() {
        return bottomRight.vectorTo(bottomLeft);
    }

    public Vector vectorFromBottomLeftToTop() {
        return bottomLeft.vectorTo(top);
    }
}
