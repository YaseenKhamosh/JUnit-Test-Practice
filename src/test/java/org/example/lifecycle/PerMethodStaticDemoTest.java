package org.example.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DEFAULT mode: @TestInstance(PER_METHOD).
 *
 * JUnit creates a NEW instance of this class for EACH @Test, so @BeforeAll /
 * @AfterAll have no single instance to belong to and therefore MUST be static.
 *
 * Watch the console: [BeforeAll] prints ONCE, and the two tests each report a
 * DIFFERENT instance hash -> proof that a fresh object is created per test.
 */
@DisplayName("Lifecycle demo: PER_METHOD (default, static @BeforeAll)")
public class PerMethodStaticDemoTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("[PER_METHOD] @BeforeAll (static) -- runs ONCE");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("[PER_METHOD] @AfterAll  (static) -- runs ONCE");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("[PER_METHOD] @BeforeEach on instance " + System.identityHashCode(this));
    }

    @Test
    void testOne() {
        System.out.println("[PER_METHOD] testOne  runs on instance " + System.identityHashCode(this));
    }

    @Test
    void testTwo() {
        System.out.println("[PER_METHOD] testTwo  runs on instance " + System.identityHashCode(this));
    }
}
