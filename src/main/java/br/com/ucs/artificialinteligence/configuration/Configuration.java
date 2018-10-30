package br.com.ucs.artificialinteligence.configuration;

import br.com.ucs.artificialinteligence.activationfunction.ActivationFunction;
import br.com.ucs.artificialinteligence.activationfunction.Sigmoid;

public final class Configuration {

    public static final String TRAINING_FILE = "pendigits.tra";

    public static final String TEST_FILE = "pendigits.tes";

    public static final double LEARNING_RATE = 0.2;

    public static final int NUMBER_EPOCHS = 10;

    public static final ActivationFunction ACTIVATION_FUNCTION = new Sigmoid();
}