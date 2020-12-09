package io.torbin.fractals.svg;

import io.torbin.fractals.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Percentage.withPercentage;

@ExtendWith(MockitoExtension.class)
class TriangleTest {

    private Triangle cut;

    @Test
    void shouldPresentItselfAsSetOfSvgLineElements() {
        cut = new Triangle(
                new Point(BigDecimal.ZERO, new BigDecimal("50")),
                new Point(new BigDecimal("100"), new BigDecimal("100")),
                new Point(BigDecimal.ZERO, new BigDecimal("100"))
        );

        String result = cut.asSvg();

        assertThat(result).containsPattern("<line x1=\"0\" y1=\"50\" x2=\"100\" y2=\"100\" stroke=\".*\" />");
        assertThat(result).containsPattern("<line x1=\"100\" y1=\"100\" x2=\"0\" y2=\"100\" stroke=\".*\" />");
        assertThat(result).containsPattern("<line x1=\"0\" y1=\"100\" x2=\"0\" y2=\"50\" stroke=\".*\" />");
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