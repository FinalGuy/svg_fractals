package io.torbin.fractals.svg;

import io.torbin.fractals.Line;
import io.torbin.fractals.Point;
import io.torbin.fractals.Vector;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

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

    public static Triangle initialTriangleCenteredAt(Point center) {
        Vector toTop = new Vector(BigDecimal.ZERO, new BigDecimal("-250"));
        Point top = center.moveBy(toTop);
        Point bottomRight = center.moveBy(toTop.rotateBy120Degree());
        Point bottomLeft = center.moveBy(toTop.rotateBy240Degree());
        return new Triangle(top, bottomRight, bottomLeft);
    }

    public String asSvg() {
        return new Line(top, bottomRight).asSvg() +
                "\n\t" +
                new Line(bottomRight, bottomLeft).asSvg() +
                "\n\t" +
                new Line(bottomLeft, top).asSvg() +
                "\n\t";
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
