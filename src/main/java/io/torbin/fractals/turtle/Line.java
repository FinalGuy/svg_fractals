package io.torbin.fractals.turtle;

public class Line {

    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public String asSvg() {
        return String.format("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" stroke=\"red\" stroke-width=\"2\" />",
                start.x(),
                start.y(),
                end.x(),
                end.y());
    }

}