package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelFindIndexTest {

    @Test
    void whenElementIsInt() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int exp = ParallelFindIndex.searchIndex(array, 2);
        assertThat(exp).isEqualTo(1);
    }

    @Test
    void whenElementIsString() {
        String[] array = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        int exp = ParallelFindIndex.searchIndex(array, "2");
        assertThat(exp).isEqualTo(1);
    }
    
    @Test
    void whenUseLinearFind() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int exp = ParallelFindIndex.searchIndex(array, 2);
        assertThat(exp).isEqualTo(1);
    }

    @Test
    void whenUseParallelFind() {
        String[] array = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        int exp = ParallelFindIndex.searchIndex(array, "12");
        assertThat(exp).isEqualTo(11);
    }

    @Test
    void whenElementNotFound() {
        String[] array = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        int exp = ParallelFindIndex.searchIndex(array, "12");
        assertThat(exp).isEqualTo(-1);
    }

}