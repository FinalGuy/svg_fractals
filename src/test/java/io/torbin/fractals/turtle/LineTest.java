package io.torbin.fractals.turtle;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {

    private Line cut;

    @Test
    void shouldKnowHowToRenderItselfAsSvgLine() {
        cut = new Line(new Point(BigDecimal.ZERO, BigDecimal.ONE), new Point(BigDecimal.TEN, BigDecimal.TEN));

        String result = cut.asSvg();

        assertThat(result).matches("<line x1=\"0.0\" y1=\"1.0\" x2=\"10.0\" y2=\"10.0\" (stroke=\".*\")?(stroke-width=\".*\")? />");
    }

}