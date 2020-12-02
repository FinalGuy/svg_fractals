package koch.snowflake;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TriangleIdTest {

    private TriangleId cut;

    @ParameterizedTest
    @CsvSource({"23,23.1,23.2,23.3", "α,α.1,α.2,α.3", "foo.bar,foo.bar.1,foo.bar.2,foo.bar.3"})
    void shouldBeAbleToConstructItsChildren(String start,
                                            String expectedFirstChild,
                                            String expectedSecondChild,
                                            String expectedThirdChild) {
        cut = new TriangleId(start);
        assertThat(cut.firstChild()).isEqualTo(new TriangleId(expectedFirstChild));
        assertThat(cut.secondChild()).isEqualTo(new TriangleId(expectedSecondChild));
        assertThat(cut.thirdChild()).isEqualTo(new TriangleId(expectedThirdChild));
    }

    @ParameterizedTest
    @CsvSource({"1.2.3.4, 4, true", "1.2.3.4, 3, false", "1.2.3.4, 5, false"})
    void shouldKnowWhetherItsRelevantForIteration(String id, int iterationToDecide, boolean expected) {
        cut = new TriangleId(id);

        boolean result = cut.isRelevantForIteration(iterationToDecide);

        assertThat(result).isEqualTo(expected);
    }
}