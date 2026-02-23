package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private final int maxSize = 3;
    private CircularQueue queue;
    private Random random = new Random();

    @BeforeEach
    public void init() {
        this.queue = new CircularQueueImpl(maxSize);
    }

    @Test
    public void shouldBeInitiallyEmpty() {
        assertTrue(this.queue.isEmpty());
    }

    @Test
    public void shouldThrowErrorWhenMaxSizeIs0() {
        final int invalidMaxSize = 0;
        assertThrows(IllegalArgumentException.class, () -> new CircularQueueImpl(invalidMaxSize));
    }

    @Test
    public void shouldPushAnElement() {
        final int element = random.nextInt();
        this.queue.push(element);
        assertFalse(this.queue.isEmpty());
    }
}
