package br.com.ucs.artificialinteligence.activationfunction;

public final class Tanh implements ActivationFunction {

    @Override
    public Double apply(final Double activation) {
        return 2 * new Sigmoid().apply(2 * activation) - 1;
    }
}
