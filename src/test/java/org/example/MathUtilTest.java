package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilTest {

    MathUtil mathUtil;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up resources before all tests");
    }

    @BeforeEach
    void init() {
        mathUtil = new MathUtil();
    }

    @AfterEach
    void tearDown() {
        System.out.println("cleaning up after test");
    }


    @Nested
    @DisplayName("Covering addition operations")
    class AdditionTests {
        @Test
        @DisplayName("Validates the addition of two positive numbers")
        public void testAddPositiveNumbers() {
            int expected = 5;
            int actual = mathUtil.add(2, 3);
            assertEquals(expected, actual, "The sum of 2 + 3 should be 5");
        }

        @Test
        @DisplayName("Validates the addition of a positive and a negative number")
        public void testAddPositiveAndNegativeNumbers() {
            int expected = 1;
            int actual = mathUtil.add(3, -2);
            assertEquals(expected, actual, () -> "The sum of 3 + (-2) should be 1");
        }
    }
    @Test
    @DisplayName("Validates the addition of two numbers")
    public void testAdd() {
        int expected = 2;
        int actual = mathUtil.add(1,1);

        assertEquals(expected, actual, "The sum of 1 + 1 should be 2");
    }

    @Test
    @DisplayName("Validates the multiplication of two numbers")
    void testMultiply() {
        assertAll(
                () -> assertEquals(6, mathUtil.multiply(2, 3), "2 * 3 should equal 6"),
                () -> assertEquals(0, mathUtil.multiply(0, 5), "0 * 5 should equal 0"),
                () -> assertEquals(-15, mathUtil.multiply(-3, 5), "-3 * 5 should equal -15")
        );
    }

    @Test
    @DisplayName("Validates the division of two numbers")
    void testDivide(){
        assertThrows(ArithmeticException.class, () -> mathUtil.divide(1, 0), "Division by zero should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Validates the computation of circle area")
    public void testComputeCircleArea() {
        assertEquals(3.141592653589793, mathUtil.computeCircleArea( 1), "The area of a circle with radius 1 should be π");
    }
}
