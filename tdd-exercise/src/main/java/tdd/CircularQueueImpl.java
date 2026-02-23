package tdd;

import java.util.LinkedList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {
    private static final int MIN_QUEUE_SIZE = 0;
    private final int maxSize;
    private final List<Integer> elements;

    public CircularQueueImpl(int maxSize) {
        if (maxSize <= MIN_QUEUE_SIZE) {
            throw new IllegalArgumentException("Max size should be greater than 0!");
        }
        this.maxSize = maxSize;
        this.elements = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public void push(int element) {
        if (this.size() == maxSize) {
            this.elements.removeFirst();
        }
        this.elements.addLast(element);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public List<Integer> values() {
        return List.copyOf(this.elements);
    }

    @Override
    public int pop() {
        return this.elements.removeFirst();
    }

    @Override
    public int peek() {
        return this.elements.getFirst();
    }
}
