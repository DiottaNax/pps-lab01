package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}