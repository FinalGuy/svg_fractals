package koch.snowflake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private Point cut;

    @ParameterizedTest
    @CsvSource({"23,42", "1,10", "-3, -4"})
    @DisplayName("should present itself as comma separated coordinates")
    void shouldPresentItselfAsCommaSeparatedCoordinates(String x, String y) {
        cut = new Point(new BigDecimal(x), new BigDecimal(y));
        String result = cut.asCommaSeparatedCoordinates();
        assertThat(result).isEqualTo(x + ',' + y);
    }

}