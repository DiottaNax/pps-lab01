package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {
    private final int maxAttempts;
    private boolean locked;
    private Optional<Integer> pin = Optional.empty();
    private int failedAttempts = 0;

    public SmartDoorLockImpl(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void setPin(final int pin) {
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        if (this.locked) {
            this.locked = !(this.pin.get() == pin);
            this.failedAttempts += this.locked ? 1 : 0;
        }
    }

    @Override
    public void lock() {
        if (!this.pin.isPresent()) {
            throw new IllegalStateException("No pin was set before locking!");
        }
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {

    }
}
