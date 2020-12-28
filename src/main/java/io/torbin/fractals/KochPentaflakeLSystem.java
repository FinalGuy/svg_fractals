package io.torbin.fractals;

import io.torbin.fractals.turtle.TurtleCommands;

import java.util.List;
import java.util.function.Function;

import static io.torbin.fractals.turtle.TurtleCommands.*;

/**
 * The Lindenmayer-System (or L-System for short) for constructing the Koch-Snowflake that starts with a pentagram
 * instead of a triangle.
 */
public final class KochPentaflakeLSystem implements LindenmayerSystem {

    private static final String F = String.valueOf(FORWARD);
    private static final String L = String.valueOf(ROTATE_LEFT);
    private static final String R = String.valueOf(ROTATE_RIGHT);

    private static final List<Character> NON_TERMINAL_SYMBOLS = List.of('F');

    private static final List<Character> TERMINAL_SYMBOLS = List.of('+', '-');

    // Start with a pentagram
    private static final String INITIAL_WORD = F + L +
            F + R + R +
            F + L +
            F + R + R +
            F + L +
            F + R + R +
            F + L +
            F + R + R +
            F + L +
            F;

    private static final Function<String, String> PRODUCTION_RULE = s -> s.replace(F, F + L + F + R + R + F + L + F);

    public TurtleCommands iterate(int times) {
        String result = INITIAL_WORD;
        for (int i = 0; i < times; i++) {
            result = PRODUCTION_RULE.apply(result);
        }
        return new TurtleCommands(result);
    }

}
