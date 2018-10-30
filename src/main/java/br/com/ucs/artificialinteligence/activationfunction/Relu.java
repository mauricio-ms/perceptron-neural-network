package br.com.ucs.artificialinteligence.activationfunction;

public final class Relu implements ActivationFunction {

    @Override
    public Double apply(final Double activation) {
        return Math.max(0, activation);
    }
}
