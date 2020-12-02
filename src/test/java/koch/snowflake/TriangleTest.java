package koch.snowflake;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
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

    @ParameterizedTest
    @CsvSource({"true,true", "false,false"})
    void shouldAskItsIdIfRelevantForGivenIteration(boolean answerFromId, boolean expected) {
        TriangleId triangleId = mock(TriangleId.class);
        cut = Triangle.fromCoordinates(triangleId, "0,0 10,10 20,20");

        given(triangleId.isRelevantForIteration(23)).willReturn(answerFromId);

        assertThat(cut.isRelevantForIteration(23)).isEqualTo(expected);
    }
}