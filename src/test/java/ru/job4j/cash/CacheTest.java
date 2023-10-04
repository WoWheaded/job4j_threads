package ru.job4j.cash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CacheTest {

    @Test
    public void whenUpdateModelIsOk() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        base1.setName("Dmitry");
        cache.add(base1);
        base1.setName("Anton");
        cache.update(base1);
        assertThat(cache.get(base1.getId()).getName()).isEqualTo("Anton");
    }

    @Test
    public void whenUpdateBasesWithSameIdButNotEqualsVersion() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 2);
        cache.add(base1);
        Base base2 = new Base(1, 3);
        base2.setName("Dmitry");
        assertThat(catchThrowable(() -> cache.update(base2))).isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenDeleteBasesWithSameVersionAndId() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        cache.add(base1);
        Base base2 = new Base(1, 0);
        cache.delete(base2);
        assertThat(cache.get(1)).isNull();
    }

}