package io.torbin.fractals.turtle;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.CharArrayWriter;
import java.math.BigDecimal;

import static io.torbin.fractals.turtle.Point.ORIGIN;
import static io.torbin.fractals.turtle.Vector.ONE_UNIT_ALONG_X_AXIS;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SvgTurtleTest {

    private SvgTurtle cut;

    private final CharArrayWriter writer = new CharArrayWriter();

    @BeforeEach
    void setUp() {
        cut = new SvgTurtle(writer);
    }

    @Test
    void shouldDrawALineWhenMovingForward() throws Exception {
        cut.start(ORIGIN, ONE_UNIT_ALONG_X_AXIS, RotationAngle.SIXTY_DEGREE);
        cut.moveForward();

        String expected = new Line(ORIGIN, ORIGIN.moveBy(ONE_UNIT_ALONG_X_AXIS)).asSvg();

        assertThat(writer.toString()).contains(expected);
    }

    @Test
    void shouldRotateLeft() throws Exception {
        cut.start(ORIGIN, ONE_UNIT_ALONG_X_AXIS, new RotationAngle(90));
        cut.rotateLeft();
        Vector result = cut.currentDirection();

        assertThat(result.x()).isCloseTo(BigDecimal.ZERO, Percentage.withPercentage(99.999999999));
        assertThat(result.y()).isCloseTo(new BigDecimal("-1"), Percentage.withPercentage(99.999999999));
    }

    @Test
    void shouldRotateRight() throws Exception {
        cut.start(ORIGIN, ONE_UNIT_ALONG_X_AXIS, new RotationAngle(90));
        cut.rotateRight();
        Vector result = cut.currentDirection();

        assertThat(result.x()).isCloseTo(BigDecimal.ZERO, Percentage.withPercentage(99.999999999));
        assertThat(result.y()).isCloseTo(BigDecimal.ONE, Percentage.withPercentage(99.999999999));
    }
}