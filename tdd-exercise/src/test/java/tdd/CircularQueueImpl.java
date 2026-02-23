package tdd;

import java.util.LinkedList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {
    private final int maxSize;
    private final List<Integer> queue;

    public CircularQueueImpl(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
