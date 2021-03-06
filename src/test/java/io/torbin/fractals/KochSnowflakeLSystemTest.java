package io.torbin.fractals;

import io.torbin.fractals.turtle.TurtleCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KochSnowflakeLSystemTest {

    private KochSnowflakeLSystem cut;

    @BeforeEach
    void setUp() {
        cut = new KochSnowflakeLSystem();
    }

    @ParameterizedTest
    @CsvSource({"0, F--F--F", "1, F+F--F+F--F+F--F+F--F+F--F+F", "2, F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F"})
    void shouldProduceIterations(int times, String expected) {
        TurtleCommands result = cut.iterate(times);
        assertThat(result).isEqualTo(new TurtleCommands(expected));
    }
}
