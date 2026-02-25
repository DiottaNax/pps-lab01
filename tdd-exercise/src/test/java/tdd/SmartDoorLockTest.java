package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private final static int EXCLUSIVE_PIN_BOUND = 10_000;
    private final static int MAX_ATTEMPTS = 3;

    private final Random random = new Random();
    private SmartDoorLock doorLock;

    @BeforeEach
    void init() {
        this.doorLock = new SmartDoorLockImpl(MAX_ATTEMPTS);
    }

    @Test
    public void shouldBeInitiallyOpen() {
        assertFalse(this.doorLock.isLocked());
    }

    @Test
    public void shouldHaveMaxAttemptsSet() {
        assertEquals(MAX_ATTEMPTS, this.doorLock.getMaxAttempts());
    }

    @Test
    public void shouldThrowErrorIfLockedWithoutSettingPin() {
        assertThrows(IllegalStateException.class, this.doorLock::lock);
    }

    private int lockDoor() {
        final int pin = random.nextInt(EXCLUSIVE_PIN_BOUND);
        this.doorLock.setPin(pin);
        this.doorLock.lock();
        return pin;
    }

    @Test
    public void canBeLockedAfterSettingPin() {
        this.lockDoor();
        assertTrue(this.doorLock.isLocked());
    }

    @Test
    public void canBeUnLocked() {
        final int pin = this.lockDoor();
        this.doorLock.unlock(pin);
        assertFalse(this.doorLock.isLocked());
    }

    @Test
    public void nothingShouldHappenIfUnlockingADoorNotLocked() {
        this.doorLock.unlock(random.nextInt());
        assertEquals(0, this.doorLock.getFailedAttempts());
    }

    @Test
    public void failedAttemptsShouldIncreaseWithWrongPin() {
        final int pin = this.lockDoor();
        final int wrongPin = pin + 1;
        this.doorLock.unlock(wrongPin);
        assertEquals(1, this.doorLock.getFailedAttempts());
    }

    private int blockDoor() {
        final int pin = this.lockDoor();
        final int wrongPin = pin + 1;
        IntStream.range(0, this.doorLock.getMaxAttempts()).forEach(i -> this.doorLock.unlock(wrongPin));
        return pin;
    }

    @Test
    public void shouldBeBlockedWithTooManyFailedAttempts() {
        this.blockDoor();
        assertTrue(this.doorLock.isBlocked());
    }

    @Test
    public void cannotBeUnlockedWhenBlocked() {
        final int pin = this.blockDoor();
        this.doorLock.unlock(pin);
        assertTrue(this.doorLock.isLocked());
    }

    @Test
    public void canResetToInitialState() {
        this.blockDoor();
        this.doorLock.reset();
        assertFalse(this.doorLock.isLocked());
        assertFalse(this.doorLock.isBlocked());
        assertEquals(0, this.doorLock.getFailedAttempts());
    }
}
