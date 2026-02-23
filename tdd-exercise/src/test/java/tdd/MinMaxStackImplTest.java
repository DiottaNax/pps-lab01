package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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

    private List<Integer> addRandomElements(final MinMaxStack stack, final int numElements) {
        final List<Integer> elements = IntStream.range(0, numElements).mapToObj(i -> random.nextInt()).toList();
        elements.forEach(stack::push);
        return elements;
    }

    @Test
    public void shouldPushElements() {
        final int numElements = 10;
        this.addRandomElements(this.stack, numElements);
        assertEquals(numElements, this.stack.size());
    }

    @Test
    public void shouldThrowErrorWhenPoppingFromEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::pop);
    }

    @Test
    public void shouldPopElementsInLIFOOrder() {
        final int numElements = 10;
        final var addedElements = this.addRandomElements(this.stack, numElements);
        final var expected = addedElements.reversed();
        final var actual = IntStream.range(0, numElements).mapToObj(i -> this.stack.pop()).toList();
        assertEquals(numElements, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowErrorWhenPeekingIntoAnEmptyStack() {
        assertThrows(IllegalStateException.class, this.stack::peek);
    }

    @Test
    public void shouldAlwaysPeekTheSameElement() {
        this.shouldPushElements();
        final int sizeBeforePeek = this.stack.size();
        final int firstPeekedElement = this.stack.peek();
        assertEquals(sizeBeforePeek, this.stack.size());
        assertEquals(firstPeekedElement, this.stack.peek());
    }

    @Test
    public void shouldReturnMin() {
        final int numElements = 10;
        final var addedElements = this.addRandomElements(this.stack, numElements);
        assertEquals(Collections.min(addedElements), this.stack.getMin());
    }

    private void compareBenchmarks(final Runnable function, final Runnable otherFunction, final int tolerance) {
        long start = System.currentTimeMillis();
        function.run();
        final long elapsedTimeFirstFunction = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        otherFunction.run();
        final long elapsedTimeSecondFunction = System.currentTimeMillis() - start;
        final long delta = Math.abs(elapsedTimeFirstFunction - elapsedTimeSecondFunction);
        assertTrue(delta < tolerance);
    }

    @Test
    public void shouldRetrieveMinInConstantTime() {
        final int toleranceMillis = 1;
        final int fewElements = 20;
        this.addRandomElements(this.stack, fewElements);
        final int manyElements = 10_000;
        final var stackWithManyElements = new MinMaxStackImpl();
        this.addRandomElements(stackWithManyElements, manyElements);
        compareBenchmarks(this.stack::getMin, stackWithManyElements::getMin, toleranceMillis);
    }

    @Test
    public void shouldReturnMax() {
        final int numElements = 10;
        final var addedElements = this.addRandomElements(this.stack, numElements);
        assertEquals(Collections.max(addedElements), this.stack.getMax());
    }
}