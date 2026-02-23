package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private final Random random = new Random();
    private MinMaxStack stack;

    @BeforeEach
    public void init() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void shouldInitAnEmptyStack() {
        assertTrue(this.stack.isEmpty());
    }

    private List<Integer> addRandomElements(final int numElements) {
        final List<Integer> elements = IntStream.range(0, numElements).mapToObj(i -> random.nextInt()).toList();
        elements.forEach(this.stack::push);
        return elements;
    }

    @Test
    public void shouldPushElements() {
        final int numElements = 10;
        this.addRandomElements(numElements);
        assertEquals(numElements, this.stack.size());
    }

    @Test
    public void shouldThrowErrorWhenPoppingFromEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::pop);
    }

    @Test
    public void shouldPopElementsInLIFOOrder() {
        final int numElements = 10;
        final var addedElements = this.addRandomElements(numElements);
        final var expected = addedElements.reversed();
        final var actual = IntStream.range(0, numElements).mapToObj(i -> this.stack.pop()).toList();
        assertEquals(numElements, actual.size());
        assertEquals(expected, actual);
    }
}