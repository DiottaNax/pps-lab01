package tdd;

import java.util.LinkedList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    private final List<Integer> stack;

    public MinMaxStackImpl() {
        this.stack = new LinkedList<>();
    }

    @Override
    public void push(int value) {
        this.stack.addLast(value);
    }

    private void verifyStackNonEmpty() {
        if (this.stack.isEmpty()) {
            throw new IllegalStateException("The stack is empty!");
        }
    }

    @Override
    public int pop() {
        this.verifyStackNonEmpty();
        return this.stack.removeLast();
    }

    @Override
    public int peek() {
        this.verifyStackNonEmpty();
        return this.stack.getLast();
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
