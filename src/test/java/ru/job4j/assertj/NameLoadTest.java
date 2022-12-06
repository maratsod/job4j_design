package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy((nameLoad::getMap))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmpty1() {
        NameLoad nameLoad = new NameLoad();
        String word = "name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("symbol");
    }

    @Test
    void checkEmpty2() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void checkEmpty3() {
        NameLoad nameLoad = new NameLoad();
        String word = "=name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("key");
    }

    @Test
    void checkEmpty13() {
        NameLoad nameLoad = new NameLoad();
        String word = "name=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(word)
                .hasMessageContaining("value");
    }
}