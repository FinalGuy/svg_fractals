package koch.snowflake;

import java.util.ArrayList;
import java.util.List;

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

    public List<Line> applyIteration() {
        List<Line> lines = new ArrayList<>(4);
        Vector oneThirdFromStartToEnd = start.vectorTo(end).scaleToOneThird();
        Line first = new Line(this.start, this.start.moveBy(oneThirdFromStartToEnd));
        Line second = new Line(first.end, first.end.moveBy(oneThirdFromStartToEnd.rotateByDegree(-60d)));
        Line third = new Line(second.end, first.end.moveBy(oneThirdFromStartToEnd));
        Line fourth = new Line(third.end, this.end);
        lines.add(first);
        lines.add(second);
        lines.add(third);
        lines.add(fourth);
        return lines;
    }

}
