package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean locked;

    @Override
    public void setPin(int pin) {

    }

    @Override
    public void unlock(int pin) {

    }

    @Override
    public void lock() {
        if(!locked) {
            throw new IllegalStateException("No pin was set before locking!");
        }
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
