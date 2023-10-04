package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T target;

    public ParallelFindIndex(T[] array, int from, int to, T target) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    private int findIndexByLinear() {
        int result = -1;
        for (int i = from; i < to; i++) {
            if (target.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return findIndexByLinear();
        }
        int mid = (from + to) / 2;
        ParallelFindIndex<T> leftFindIndex = new ParallelFindIndex<>(array, from, mid, target);
        ParallelFindIndex<T> rightFindIndex = new ParallelFindIndex<>(array, mid + 1, to, target);
        leftFindIndex.fork();
        rightFindIndex.fork();
        Integer leftIndex = leftFindIndex.join();
        Integer rightIndex = rightFindIndex.join();
        return Math.max(leftIndex, rightIndex);
    }

    public static <T> int searchIndex(T[] array, T target) {
        return new ForkJoinPool().invoke(new ParallelFindIndex<T>(array, 0, array.length, target));
    }
}
