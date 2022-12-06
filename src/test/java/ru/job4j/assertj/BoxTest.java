package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isNotEmpty()
                .contains("dron")
                .doesNotContain("java")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotBlank()
                .startsWith("Cu")
                .endsWith("be");
    }

    @Test
    void testNumber10() {
        Box box = new Box(4, 10);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(4)
                .isLessThan(11)
                .isGreaterThan(3);
    }

    @Test
    void testNumber8() {
        Box box = new Box(8, 10);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(8)
                .isEven()
                .isGreaterThan(5)
                .isLessThan(9);
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean res = box.isExist();
        assertThat(res).isTrue()
                .isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(-4, 10);
        boolean res = box.isExist();
        assertThat(res).isFalse()
                .isEqualTo(false);
    }

    @Test
    void testArea10() {
        Box box = new Box(0, 10);
        double num = box.getArea();
        assertThat(num).isEqualTo(1256.6d, withPrecision(0.1d))
                .isCloseTo(1256.6d, withPrecision(0.1d))
                .isGreaterThan(5.25d)
                .isLessThan(1265.26d);
    }

    @Test
    void testArea5() {
        Box box = new Box(8, 5);
        double num = box.getArea();
        assertThat(num).isEqualTo(150)
                .isCloseTo(150, withPrecision(0.1d))
                .isGreaterThan(140)
                .isLessThan(160);
    }
}