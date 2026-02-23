package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private final int maxSize = 3;
    private CircularQueue queue;

    @BeforeEach
    public void init() {
        this.queue = new CircularQueueImpl(maxSize);
    }

    @Test
    public void shouldBeInitiallyEmpty() {
        assertTrue(this.queue.isEmpty());
    }
}
