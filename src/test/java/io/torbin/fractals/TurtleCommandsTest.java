package io.torbin.fractals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.torbin.fractals.RotationAngle.SIXTY_DEGREE;
import static io.torbin.fractals.Point.ORIGIN;
import static io.torbin.fractals.Vector.ONE_UNIT_ALONG_X_AXIS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TurtleCommandsTest {

    private TurtleCommands cut;

    @Mock
    private Turtle turtle;


    @Test
    void shouldAlwaysCallStartAndEndWhenApplying() throws Exception {
        cut = new TurtleCommands("");

        cut.applyTo(turtle, ORIGIN, ONE_UNIT_ALONG_X_AXIS, SIXTY_DEGREE);

        InOrder inOrder = inOrder(turtle);
        inOrder.verify(turtle).start(ORIGIN, ONE_UNIT_ALONG_X_AXIS, SIXTY_DEGREE);
        inOrder.verify(turtle).end();
    }

    @Test
    void shouldTranslateCommandSymbolsToMethodCallsCorrectly() throws Exception {
        cut = new TurtleCommands("F+F--F+++F--FF");

        cut.applyTo(turtle, ORIGIN, ONE_UNIT_ALONG_X_AXIS, SIXTY_DEGREE);

        InOrder inOrder = inOrder(turtle);
        inOrder.verify(turtle).start(ORIGIN, ONE_UNIT_ALONG_X_AXIS, SIXTY_DEGREE);
        inOrder.verify(turtle).moveForward();
        inOrder.verify(turtle).rotateRight();
        inOrder.verify(turtle).moveForward();
        inOrder.verify(turtle, times(2)).rotateLeft();
        inOrder.verify(turtle).moveForward();
        inOrder.verify(turtle, times(3)).rotateRight();
        inOrder.verify(turtle).moveForward();
        inOrder.verify(turtle, times(2)).rotateLeft();
        inOrder.verify(turtle, times(2)).moveForward();
        inOrder.verify(turtle).end();
    }

    @Test
    void shouldThrowIllegalStateExceptionForUnknownSymbols() {
        cut = new TurtleCommands("Foo");

        assertThatThrownBy(() -> cut.applyTo(turtle, ORIGIN, ONE_UNIT_ALONG_X_AXIS, SIXTY_DEGREE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown command symbol: o");
    }
}