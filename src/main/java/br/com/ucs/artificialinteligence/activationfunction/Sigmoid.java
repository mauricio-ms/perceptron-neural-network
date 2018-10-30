package br.com.ucs.artificialinteligence.activationfunction;

public final class Sigmoid implements ActivationFunction {

    @Override
    public Double apply(final Double activation) {
        return 1.0 / (1.0 + Math.exp(-activation));
    }
}
