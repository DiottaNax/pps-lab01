package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private final int maxSize = 3;
    private final Random random = new Random();
    private CircularQueue queue;

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

    private List<Integer> addRandomElements(final int numElements) {
        final var elements = IntStream.range(0, numElements)
                .mapToObj(i -> this.random.nextInt())
                .collect(Collectors.toList());
        elements.forEach(this.queue::push);
        return elements;
    }

    @Test
    public void shouldNotAddMoreThanMaxSizeElements() {
        final int numElements = this.maxSize + 1;
        addRandomElements(numElements);
        assertEquals(maxSize, this.queue.size());
    }

    @Test
    public void shouldOverrideOldestElements() {
        final int numElements = 10;
        final var addedElements = this.addRandomElements(numElements);
        final var expectedElements = addedElements.subList(numElements - maxSize, numElements);
        assertEquals(expectedElements, this.queue.values());
    }

    @Test
    public void shouldPopElementsInFIFOOrder() {
        final int numElements = 3;
        final var expected = this.addRandomElements(numElements);
        final var actual = IntStream.range(0, numElements)
                .mapToObj(i -> this.queue.pop())
                .collect(Collectors.toList());
        assertEquals(expected, actual);
    }
}
