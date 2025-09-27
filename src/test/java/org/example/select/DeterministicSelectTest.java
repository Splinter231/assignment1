package org.example.select;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testMedianOfMediansSelect() {
        int[] arr = {7, 2, 9, 4, 3, 8, 5, 1, 6};
        assertEquals(5, DeterministicSelect.select(arr.clone(), 4)); // 5-й по величине элемент
    }

    @Test
    void testSelectSmallArray() {
        int[] arr = {3, 1, 2};
        assertEquals(1, DeterministicSelect.select(arr.clone(), 0));
        assertEquals(2, DeterministicSelect.select(arr.clone(), 1));
        assertEquals(3, DeterministicSelect.select(arr.clone(), 2));
    }

    @Test
    void testSelectSingleElement() {
        int[] arr = {42};
        assertEquals(42, DeterministicSelect.select(arr.clone(), 0));
    }
}
