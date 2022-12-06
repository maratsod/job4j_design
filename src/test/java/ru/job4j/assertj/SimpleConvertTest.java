package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .anySatisfy(e -> {
                    assertThat(e.length()).isLessThan(5);
                    assertThat(e.length()).isEqualTo(4);
                })
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                })
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .startsWith("first", "second")
                .doesNotContain("s", Index.atIndex(1));
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "three")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKey("six")
                .doesNotContainValue(6)
                .containsEntry("first", 0);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three");
        assertThat(set).hasSize(3)
                .isNotEmpty()
                .contains("first")
                .containsExactlyInAnyOrder("second","first", "three")
                .containsOnly("second","first", "three")
                .doesNotContain("six", "seven")
                .allMatch(e -> e.length() < 10)
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() < 1);
    }
}