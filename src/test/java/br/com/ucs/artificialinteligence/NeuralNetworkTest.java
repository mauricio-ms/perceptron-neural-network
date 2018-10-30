package br.com.ucs.artificialinteligence;

import org.junit.Assert;
import org.junit.Test;

public class NeuralNetworkTest {

    private final double[][][] weights = {
            {
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                    {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3}
            },
            {
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0},
                    {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0}
            }
    };

    private final double[] input = {
            2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0
    };

    @Test
    public void afterForwardPropagateOutputsMustBeUpdated() {
        final NeuralNetwork neuralNetwork = new NeuralNetwork(weights);
        neuralNetwork.forwardPropagate(input);

        final double[][] outputs = {
                {2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0},
                {
                        neuralNetwork.transfer(27.20),
                        neuralNetwork.transfer(54.40),
                        neuralNetwork.transfer(81.60),
                        neuralNetwork.transfer(180.80),
                        neuralNetwork.transfer(136.0),
                        neuralNetwork.transfer(163.20),
                        neuralNetwork.transfer(190.40),
                        neuralNetwork.transfer(217.60),
                        neuralNetwork.transfer(244.80),
                        neuralNetwork.transfer(272.0),
                        neuralNetwork.transfer(299.20),
                        neuralNetwork.transfer(326.40),
                        neuralNetwork.transfer(353.60)
                },
                {
                        neuralNetwork.transfer(26.0),
                        neuralNetwork.transfer(52.0),
                        neuralNetwork.transfer(78.0),
                        neuralNetwork.transfer(104.30),
                        neuralNetwork.transfer(130.0),
                        neuralNetwork.transfer(156.0),
                        neuralNetwork.transfer(182.0),
                        neuralNetwork.transfer(208.0),
                        neuralNetwork.transfer(234.0),
                        neuralNetwork.transfer(260.0)
                }
        };
        neuralNetwork.printOutputs();
        Assert.assertArrayEquals(outputs, neuralNetwork.getOutputs());
    }

    @Test
    public void createErrorsFactorToTheOutputLayerShouldReturnDifferenceBetweenOutputAndExpected() {
        final NeuralNetwork neuralNetwork = new NeuralNetwork(weights);
        neuralNetwork.forwardPropagate(input);

        final int[] expected = {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 1
        };
        final double[] errorsFactor = neuralNetwork.createErrorsFactor(2, expected);

        final double[] errorsFactorExpected = {
                -neuralNetwork.transfer(26.0),
                -neuralNetwork.transfer(52.0),
                -neuralNetwork.transfer(78.0),
                -neuralNetwork.transfer(104.30),
                -neuralNetwork.transfer(130.0),
                -neuralNetwork.transfer(156.0),
                -neuralNetwork.transfer(182.0),
                -neuralNetwork.transfer(208.0),
                -neuralNetwork.transfer(234.0),
                1 - neuralNetwork.transfer(260.0)
        };

        Assert.assertArrayEquals(errorsFactorExpected, errorsFactor, 0.0);
    }

    @Test
    public void createErrorsFactorToTheHiddenLayerShouldReturnTheErrorResponsibilityToTheEachNeuron() {
        final double[][] deltas = {
                {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6},
                {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3},
                {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0}
        };
        final NeuralNetwork neuralNetwork = new NeuralNetwork(weights, deltas);

        final int[] expected = {
                0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0
        };
        final double[] errorsFactor = neuralNetwork.createErrorsFactor(1, expected);

        final double[] errorsFactorExpected = {
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0,
                77.0
        };

        Assert.assertArrayEquals(errorsFactorExpected, errorsFactor, 0.0);
    }
}