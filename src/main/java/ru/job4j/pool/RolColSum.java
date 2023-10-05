package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    private static void count(int i, int[][] matrix, Sums[] sums) {
        sums[i] = new Sums(0, 0);
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowSum += matrix[i][j];
            colSum += matrix[j][i];
        }
        sums[i].setRowSum(rowSum);
        sums[i].setColSum(colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            count(i, matrix, sums);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int resultIndex = i;
            CompletableFuture.runAsync(() -> count(resultIndex, matrix, sums));
        }
        return sums;
    }
}