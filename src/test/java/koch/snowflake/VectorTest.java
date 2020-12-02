package koch.snowflake;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

class VectorTest {

    private Vector cut;

    @Test
    void shouldKnowHowToRotate() {
        // Vector along the y-axis pointing from zero up to one
        cut = new Vector(BigDecimal.ZERO, BigDecimal.ONE);

        // rotating it clockwise by 90° so it's now is aligned with the x-axis pointing from zero to one.
        Vector result = cut.rotateByDegree(-90.0d);

        assertThat(result.x()).isCloseTo(BigDecimal.ONE, withPercentage(99.99999d));
        assertThat(result.y()).isCloseTo(BigDecimal.ZERO, withPercentage(99.99999d));
    }

}