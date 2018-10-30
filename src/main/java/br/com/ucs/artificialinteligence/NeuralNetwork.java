package br.com.ucs.artificialinteligence;

import br.com.ucs.artificialinteligence.configuration.Configuration;
import br.com.ucs.artificialinteligence.model.Data;
import br.com.ucs.artificialinteligence.results.ConfusionMatrix;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public final class NeuralNetwork {

    private double[][] outputs;

    private double[][][] weights;

    private double[][] deltas;

    public NeuralNetwork() {
        this(null);
    }

    public NeuralNetwork(final double[][][] weights) {
        this.weights = weights == null ? buildRandomWeights() : weights;
        outputs = new double[3][];
        outputs[0] = new double[16];
        outputs[1] = new double[13];
        outputs[2] = new double[10];

        deltas = new double[2][];
        deltas[0] = new double[13];
        deltas[1] = new double[10];
    }

    public NeuralNetwork(final double[][][] weights,
                         final double[][] deltas) {
        this.weights = weights;
        outputs = new double[3][];
        outputs[0] = new double[16];
        outputs[1] = new double[13];
        outputs[2] = new double[10];
        this.deltas = deltas;
    }

    private double[][][] buildRandomWeights() {
        final double[][][] randomWeights = new double[2][][];
        randomWeights[0] = new double[16][13];
        for (int i = 0; i < randomWeights[0].length; i++) {
            for (int j = 0; j < randomWeights[0][i].length; j++) {
                randomWeights[0][i][j] = (double) new Random().nextInt(100) / 100;
            }
        }

        randomWeights[1] = new double[13][10];
        for (int i = 0; i < randomWeights[1].length; i++) {
            for (int j = 0; j < randomWeights[1][i].length; j++) {
                randomWeights[1][i][j] = (double) new Random().nextInt(100) / 100;
            }
        }
        return randomWeights;
    }

    public double[] forwardPropagate(double[] input) {
        // Initialize input layer with inputs
        for (int i = 0; i < outputs[0].length; i++) {
            outputs[0][i] = input[i];
        }

        // Apply the inputs to the other layers
        for (int i = 1; i <= weights.length; i++) {
            for (int j = 0; j < outputs[i].length; j++) {
                final int layer = i - 1;
                final double activation = activate(layer, j, input);
                final double output = transfer(activation);
                outputs[i][j] = output;
            }
            input = outputs[i];
        }

        return outputs[outputs.length - 1];
    }

    private double activate(final int layer,
                            final int neuron,
                            final double[] input) {
        double activation = 0.0;
        for (int i = 0; i < weights[layer].length; i++) {
            final double weight = weights[layer][i][neuron];
            activation += weight * input[i];
        }

        return activation;
    }

    public double transfer(final double activation) {
        return Configuration.ACTIVATION_FUNCTION.apply(activation);
    }

    public void backwardPropagateError(final int[] expected) {
        for (int i = weights.length; i > 0; i--) {
            final double[] errorsFactor = createErrorsFactor(i, expected);

            for (int j = 0; j < deltas[i - 1].length; j++) {
                deltas[i - 1][j] = errorsFactor[j] * transferDerivative(outputs[i][j]);
            }
        }
    }

    private double transferDerivative(final double output) {
        return output * (1.0 - output);
    }

    public double[] createErrorsFactor(final int layer,
                                       final int[] expected) {
        if (layer == 2) {
            return createErrorsFactorToTheOutputLayer(expected);
        }

        // Create errorsFactors from the other layers
        final double[] errorFactors = new double[outputs[layer].length];
        for (int i = outputs[layer].length - 1; i >= 0; i--) {
            double errorFactor = 0.0;
            for (int j = 0; j < weights[layer][i].length; j++) {
                final double weight = weights[layer][i][j];
                errorFactor += deltas[layer][j] * weight;
            }
            errorFactors[i] = errorFactor;
        }
        return errorFactors;
    }

    private double[] createErrorsFactorToTheOutputLayer(final int[] expected) {
        final double[] outputLayer = outputs[outputs.length - 1];
        final double[] errorFactors = new double[outputLayer.length];
        for (int i = 0; i < outputLayer.length; i++) {
            errorFactors[i] = expected[i] - outputLayer[i];
        }
        return errorFactors;
    }

    public void updateWeights(final double learningRate) {

        for (int i = 0; i < outputs[1].length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[0][j][i] = weights[0][j][i] + learningRate * outputs[0][j] * deltas[0][i];
            }
        }

        for (int i = 0; i < outputs[2].length; i++) {
            for (int j = 0; j < weights[1].length; j++) {
                weights[1][j][i] = weights[1][j][i] + learningRate * outputs[1][j] * deltas[1][i];
            }
        }
    }

    private void trainNetworkFromFile(final String trainingFile) throws IOException {
        trainNetwork(Data.fromFile(trainingFile));
    }

    private void trainNetwork(final Data data) {
        final double[][] trainingData = data.getData();
        final int[] expected = data.getExpected();
        for (int i = 0; i < Configuration.NUMBER_EPOCHS; i++) {
            System.out.println("Epoch: " + (i + 1));
            for (int j = 0; j < trainingData.length; j++) {
                final double[] trainingRow = trainingData[j];
                final double[] output = forwardPropagate(trainingRow);
                final int[] expectedRow = buildExpected(expected[j]);
                System.out.println("Output:");
                System.out.println(Arrays.toString(output));
                System.out.println("Expected: " + expected[j]);
                System.out.println(Arrays.toString(expectedRow));
                System.out.println("Is Correct: " + isCorrect(output, expected[j]));
                backwardPropagateError(expectedRow);
                updateWeights(Configuration.LEARNING_RATE);
            }
        }
    }

    private void testNetworkFromFile(final String testFile) throws IOException {
        testNetwork(Data.fromFile(testFile));
    }

    private void testNetwork(final Data data) {
        final double[][] testData = data.getData();
        final int[] expected = data.getExpected();


        final int actual[] = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            final double[] testRow = testData[i];
            final double[] output = forwardPropagate(testRow);
            actual[i] = getIndexOfLargest(output);
        }

        final ConfusionMatrix confusionMatrix = new ConfusionMatrix(expected, actual);
        confusionMatrix.print();
        System.out.println();
        confusionMatrix.printStatistics();
    }

    private boolean isCorrect(final double[] output,
                              final int expected) {
        return getIndexOfLargest(output) == expected;
    }

    private int getIndexOfLargest(final double[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int largest = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[largest]) {
                largest = i;
            }
        }
        return largest;
    }

    private int[] buildExpected(final int classOfData) {
        final int[] expected = new int[outputs[outputs.length - 1].length];
        for (int i = 0; i < outputs[outputs.length - 1].length; i++) {
            expected[i] = classOfData == i ? 1 : 0;
        }
        return expected;
    }

    public void printOutputs() {
        for (int i = 0; i < outputs.length; i++) {
            System.out.printf("Layer %s\n", i);
            System.out.println(Arrays.toString(outputs[i]));
        }
    }

    public double[][] getOutputs() {
        return outputs;
    }

    public static void main(String[] args) throws IOException {
        final NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.trainNetworkFromFile(Configuration.TRAINING_FILE);
        neuralNetwork.testNetworkFromFile(Configuration.TEST_FILE);
    }
}