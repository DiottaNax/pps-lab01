package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean locked;
    private Optional<Integer> pin = Optional.empty();

    @Override
    public void setPin(final int pin) {
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        if (this.locked) {
            this.locked = !(this.pin.get() == pin);
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
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
