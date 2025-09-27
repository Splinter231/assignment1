package org.example.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {
    @Test
    void testRunMergesort() {
        assertDoesNotThrow(() -> Main.main(new String[]{"mergesort", "100"}));
    }

    @Test
    void testRunQuickSort() {
        assertDoesNotThrow(() -> Main.main(new String[]{"quicksort", "100"}));
    }
}
