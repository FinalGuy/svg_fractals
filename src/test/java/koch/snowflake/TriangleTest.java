package koch.snowflake;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class TriangleTest {

    private Triangle cut;

    @Test
    void shouldPresentItselfAsSvgPolygonElement() {
        cut = Triangle.fromCoordinates(TriangleId.INITIAL, "0,50 100,100 0,100");

        String result = cut.asSvgPolygon();

        assertThat(result).isEqualTo("<polygon id=\"α\" points=\"0,50 100,100 0,100\" />");
    }

    @Test
    void shouldBeConstructableFromCoordinateList() {
        Triangle expected = new Triangle(
                TriangleId.INITIAL,
                Point.fromCommaSeparatedCoordinates("0,50"),
                Point.fromCommaSeparatedCoordinates("100,100"),
                Point.fromCommaSeparatedCoordinates("0,100"));

        Triangle result = Triangle.fromCoordinates(TriangleId.INITIAL, "0,50 100,100 0,100");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldBeAbleToApplyIterationOnItself() {
        cut = Triangle.fromCoordinates(TriangleId.INITIAL, "0,50 100,100 0,100");

        List<Triangle> triangles = cut.applyIteration();

        // 3 smaller ones, one at each side.
        assertThat(triangles).hasSize(3);
        // does not contain itself
        assertThat(triangles).doesNotContain(cut);
        assertThat(triangles.get(0).idAsString()).isEqualTo(TriangleId.INITIAL.firstChild().asString());
        assertThat(triangles.get(1).idAsString()).isEqualTo(TriangleId.INITIAL.secondChild().asString());
        assertThat(triangles.get(2).idAsString()).isEqualTo(TriangleId.INITIAL.thirdChild().asString());
    }

    @Test
    void shouldBeAbleToConstructItselfAtAnyGivenPointAsCenter() {

        Triangle result = Triangle.initialTriangleCenteredAt(Point.fromCommaSeparatedCoordinates("5,5"));

        assertThat(result.vectorFromTopToBottomRight().length())
                .isCloseTo(result.vectorFromBottomRightToBottomLeft().length(), withPercentage(99.9999999999d));
        assertThat(result.vectorFromBottomRightToBottomLeft().length())
                .isCloseTo(result.vectorFromBottomLeftToTop().length(), withPercentage(99.9999999999d));
    }

}