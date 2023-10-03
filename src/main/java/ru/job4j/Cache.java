package ru.job4j;

public final class Cache {
    private static Cache cache;

    public static Cache instOf() {
        if (cache == null) {
            synchronized (Cache.class) {
                cache = new Cache();
            }
        }
        return cache;
    }
}
