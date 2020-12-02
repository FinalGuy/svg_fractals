package koch.snowflake;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class TriangleTest {

    private Triangle cut;

    @Test
    void shouldPresentItselfAsSvgPolygonElement() {
        cut = Triangle.fromCoordinates("0,50 100,100 0,100");

        String result = cut.asSvgPolygon();

        assertThat(result).isEqualTo("<polygon points=\"0,50 100,100 0,100\" />");
    }

    @Test
    void shouldBeConstructableFromCoordinateList() {
        Triangle expected = Triangle.fromCoordinates("0,50 100,100 0,100");

        Triangle result = Triangle.fromCoordinates("0,50 100,100 0,100");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldBeAbleToApplyIterationOnItself() {
        cut = Triangle.fromCoordinates("0,50 100,100 0,100");

        List<Triangle> triangles = cut.applyIteration();

        // 3 smaller ones, one at each side.
        assertThat(triangles).hasSize(3);
        // does not contain itself
        assertThat(triangles).doesNotContain(cut);
    }

    @Test
    void shouldBeAbleToConstructItselfAtAnyGivenPointAsCenter() {

        Triangle result = Triangle.centeredAt(Point.fromCommaSeparatedCoordinates("5,5"));

        assertThat(result.vectorFromTopToBottomRight().length())
                .isCloseTo(result.vectorFromBottomRightToBottomLeft().length(), withPercentage(99.9999999999d));
        assertThat(result.vectorFromBottomRightToBottomLeft().length())
                .isCloseTo(result.vectorFromBottomLeftToTop().length(), withPercentage(99.9999999999d));
    }
}