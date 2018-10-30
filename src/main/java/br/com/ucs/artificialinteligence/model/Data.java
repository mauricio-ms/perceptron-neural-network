package br.com.ucs.artificialinteligence.model;

import br.com.ucs.artificialinteligence.helpers.PathsHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public final class Data {

    private final double[][] data;

    private final int[] expected;

    private Data(final double[][] data,
                 final int[] expected) {
        this.data = data;
        this.expected = expected;
    }

    public static Data fromFile(final String fileName) throws IOException {
        final List<String> lines = Files.readAllLines(PathsHelper.getFromResources(fileName));
        final double[][] data = new double[lines.size()][];
        final int[] expected = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            final String[] values = lines.get(i).split(",");
            data[i] = new double[values.length - 1];
            for (int j = 0; j < values.length - 1; j++) {
                data[i][j] = Double.parseDouble(values[j].trim()) / 100;
            }
            expected[i] = Integer.parseInt(values[values.length - 1].trim());
        }

        return new Data(data, expected);
    }

    public double[][] getData() {
        return data;
    }

    public int[] getExpected() {
        return expected;
    }
}
