package tdd;

import java.util.LinkedList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {
    private static final int MIN_QUEUE_SIZE = 0;
    private final int maxSize;
    private final List<Integer> queue;

    public CircularQueueImpl(int maxSize) {
        if (maxSize <= MIN_QUEUE_SIZE) {
            throw new IllegalArgumentException("Max size should be greater than 0!");
        }
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public void push(int element) {
        this.queue.addLast(element);
    }
}
