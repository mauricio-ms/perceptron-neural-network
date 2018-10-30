package br.com.ucs.artificialinteligence.results;

import java.text.DecimalFormat;

public final class ConfusionMatrix {

    private static final int VP_INDEX = 0;

    private static final int VN_INDEX = 1;

    private static final int FP_INDEX = 2;

    private static final int FN_INDEX = 3;

    private static final int ACCURACY_INDEX = 0;

    private static final int ERROR_INDEX = 1;

    private static final int RECALL_INDEX = 2;

    private static final int PRECISION_INDEX = 3;

    private static final int SPECIFICITY_INDEX = 4;

    private static final int F_MEASURE_INDEX = 5;

    private final int[][] matrix;

    private final int[][] values;

    private final double[][] statistics;

    public ConfusionMatrix(final int[][] matrix) {
        this.matrix = matrix;
        values = buildValues(matrix);
        statistics = buildStatistics(values);
    }

    public ConfusionMatrix(final int[] expected,
                           final int actual[]) {
        matrix = new int[10][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[10];
        }

        for (int i = 0; i < expected.length; i++) {
            matrix[actual[i]][expected[i]] += 1;
        }

        values = buildValues(matrix);
        statistics = buildStatistics(values);
    }

    private int[][] buildValues(final int[][] matrix) {
        final int values[][] = new int[4][];
        for (int i = 0; i < values.length; i++) {
            values[i] = new int[10];
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    values[VP_INDEX][i] += matrix[i][j];
                } else {
                    values[FP_INDEX][i] += matrix[j][i];
                    values[FN_INDEX][i] += matrix[i][j];
                    values[VN_INDEX][i] += matrix[j][j];
                }
            }
        }

        return values;
    }

    private double[][] buildStatistics(final int[][] values) {
        final double[][] statistics = new double[6][];
        statistics[ACCURACY_INDEX] = new double[10];
        statistics[ERROR_INDEX] = new double[10];
        statistics[RECALL_INDEX] = new double[10];
        statistics[PRECISION_INDEX] = new double[10];
        statistics[SPECIFICITY_INDEX] = new double[10];
        statistics[F_MEASURE_INDEX] = new double[10];

        for (int i = 0; i < 10; i++) {
            final int vp = values[VP_INDEX][i];
            final int vn = values[VN_INDEX][i];
            final int fp = values[FP_INDEX][i];
            final int fn = values[FN_INDEX][i];

            statistics[ACCURACY_INDEX][i] = (double) (vp + vn) / (vp + fp + vn + fn);
            statistics[ERROR_INDEX][i] = 1 - statistics[ACCURACY_INDEX][i];
            statistics[RECALL_INDEX][i] = (double) vp / (vp + fn);
            statistics[PRECISION_INDEX][i] = (double) vp / (vp + fp);
            statistics[SPECIFICITY_INDEX][i] = (double) vn / (vn + fp);
            statistics[F_MEASURE_INDEX][i] = 2 * (statistics[RECALL_INDEX][i] * statistics[PRECISION_INDEX][i]) /
                    (statistics[RECALL_INDEX][i] + statistics[PRECISION_INDEX][i]);
        }

        return statistics;
    }

    public void print() {
        final char[] prediction = {'P', 'r', 'e', 'v', 'i', 's', 'ã', 'o'};
        System.out.print("   ");
        for (int i = 0; i < prediction.length; i++) {
            System.out.printf("%7s", prediction[i]);
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%7s", i);
        }
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("-------");
        }
        System.out.println();

        final char[] actual = {'A', 't', 'u', 'a', 'l'};
        for (int i = 0; i < matrix.length; i++) {
            if (i < actual.length) {
                System.out.printf("%s %s [", actual[i], i);
            } else {
                System.out.printf("  %s [", i);
            }
            for (int j = 0; j < matrix[i].length; j++) {
                final boolean isLast = j == matrix[i].length - 1;
                final String format = isLast ? "%5s" : "%5s, ";
                System.out.printf(format, matrix[i][j]);
            }
            System.out.println("]");
        }
    }

    public void printStatistics() {
        System.out.println("Estatísticas:\n");
        final String[] statisticsNames = new String[6];
        statisticsNames[ACCURACY_INDEX] = "Acurácia";
        statisticsNames[ERROR_INDEX] = "Erro";
        statisticsNames[RECALL_INDEX] = "Recall";
        statisticsNames[PRECISION_INDEX] = "Precisão";
        statisticsNames[SPECIFICITY_INDEX] = "Especificidade";
        statisticsNames[F_MEASURE_INDEX] = "FMeasure";

        System.out.print("   ");
        for (int i = 0; i < statisticsNames.length; i++) {
            System.out.printf("%-16s", statisticsNames[i]);
        }

        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.printf("%s  ", i);
            for (int j = 0; j < statistics.length; j++) {
                System.out.printf("%-16s", new DecimalFormat("#.#######").format(statistics[j][i]));
            }
            System.out.println();
        }
    }

    public int[][] getValues() {
        return values;
    }
}