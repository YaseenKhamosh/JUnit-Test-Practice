package org.example.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * OPT-IN mode: @TestInstance(PER_CLASS).
 *
 * JUnit creates ONE instance of this class and reuses it for every @Test, so
 * @BeforeAll / @AfterAll may be NON-static (they now have an instance to live on).
 *
 * Watch the console: hook TIMING is identical to the PER_METHOD demo -- [BeforeAll]
 * still runs ONCE -- but here both tests report the SAME instance hash, because
 * the single object is shared.
 */
@DisplayName("Lifecycle demo: PER_CLASS (non-static @BeforeAll)")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PerClassNonStaticDemoTest {

    @BeforeAll
    void beforeAll() {                 // non-static, allowed because of PER_CLASS
        System.out.println("[PER_CLASS ] @BeforeAll (non-static) -- runs ONCE on instance " + System.identityHashCode(this));
    }

    @AfterAll
    void afterAll() {                  // non-static, allowed because of PER_CLASS
        System.out.println("[PER_CLASS ] @AfterAll  (non-static) -- runs ONCE on instance " + System.identityHashCode(this));
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("[PER_CLASS ] @BeforeEach on instance " + System.identityHashCode(this));
    }

    @Test
    void testOne() {
        System.out.println("[PER_CLASS ] testOne  runs on instance " + System.identityHashCode(this));
    }

    @Test
    void testTwo() {
        System.out.println("[PER_CLASS ] testTwo  runs on instance " + System.identityHashCode(this));
    }
}
