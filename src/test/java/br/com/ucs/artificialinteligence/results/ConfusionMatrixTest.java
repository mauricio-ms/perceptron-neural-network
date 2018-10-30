package br.com.ucs.artificialinteligence.results;

import org.junit.Assert;
import org.junit.Test;

public class ConfusionMatrixTest {

    @Test
    public void assertExpectedValues() {
        final int[][] matrix = {
                {2, 3, 1, 4, 3, 2, 1, 3, 6, 7},
                {0, 3, 0, 1, 1, 0, 0, 1, 1, 0},
                {0, 0, 5, 1, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 7, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 6, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 5, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 6, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 7, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 6}
        };
        final ConfusionMatrix confusionMatrix = new ConfusionMatrix(matrix);

        final int[][] expectedValues = {
                {2, 3, 5, 7, 6, 5, 6, 8, 7, 6},
                {53, 52, 50, 48, 49, 50, 49, 47, 48, 49},
                {0, 3, 1, 7, 8, 6, 3, 7, 9, 8},
                {30, 4, 4, 2, 2, 3, 2, 0, 2, 3}
        };
        Assert.assertArrayEquals(expectedValues, confusionMatrix.getValues());
    }
}