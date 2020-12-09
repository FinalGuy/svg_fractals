package io.torbin.fractals;

import java.math.BigDecimal;
import java.math.MathContext;

public class ScalingFactor {

    private final BigDecimal factor;

    public ScalingFactor(String factor) {
        this(new BigDecimal(factor));
    }

    public ScalingFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public static ScalingFactor oneThirdPerIteration(int iterations) {
        BigDecimal oneThird = BigDecimal.ONE.divide(new BigDecimal("3"), MathContext.DECIMAL128);
        return new ScalingFactor(oneThird.pow(iterations));
    }

    public BigDecimal applyTo(BigDecimal before) {
        return before.multiply(factor);
    }
}
