package io.torbin.fractals;

import io.torbin.fractals.turtle.TurtleCommands;

import java.util.List;
import java.util.function.Function;

/**
 * The Lindenmayer-System (or L-System for short) for constructing the Koch-Snowflake that starts with a pentagram
 * instead of a triangle.
 */
public final class KochPentaflakeLSystem {

    private static final List<Character> NON_TERMINAL_SYMBOLS = List.of('F');
    private static final List<Character> TERMINAL_SYMBOLS = List.of('+', '-');
    private static final String INITIAL_WORD = "F+F--F+F--F+F--F+F--F+F";
    private static final Function<String, String> PRODUCTION_RULE = s -> s.replace("F", "F-F++F-F");

    public static TurtleCommands iterate(int times) {
        String result = INITIAL_WORD;
        for (int i = 0; i < times; i++) {
            result = PRODUCTION_RULE.apply(result);
        }
        return new TurtleCommands(result);
    }

}
