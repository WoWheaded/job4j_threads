package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.pool.RolColSum.asyncSum;
import static ru.job4j.pool.RolColSum.sum;

class RolColSumTest {
    @Test
    void thenSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18),
        };
        Sums[] actual = sum(matrix);
        assertThat(actual).contains(expected);
    }

    @Test
    void thenAsyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Sums[] expected = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18),
        };
        Sums[] actual = asyncSum(matrix);
        assertThat(actual).contains(expected);
    }
}