package koch.snowflake;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class TriangleId {

    public static final TriangleId INITIAL = new TriangleId("α");

    private final String id;

    public TriangleId(String id) {
        this.id = id;
    }

    public String asString() {
        return id;
    }

    public TriangleId firstChild() {
        return new TriangleId(id + ".1");
    }

    public TriangleId secondChild() {
        return new TriangleId(id + ".2");
    }

    public TriangleId thirdChild() {
        return new TriangleId(id + ".3");
    }

    public boolean isRelevantForIteration(int iteration) {
        return id.split("\\.").length == iteration;
    }
}
