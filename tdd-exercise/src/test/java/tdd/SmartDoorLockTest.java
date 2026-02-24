package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private final static int EXCLUSIVE_PIN_BOUND = 10_000;

    private final Random random = new Random();
    private SmartDoorLock doorLock;

    @BeforeEach
    void init() {
        this.doorLock = new SmartDoorLockImpl();
    }

    @Test
    public void shouldBeInitiallyOpen() {
        assertFalse(this.doorLock.isLocked());
    }

    @Test
    public void shouldThrowErrorIfLockedWithoutSettingPin() {
        assertThrows(IllegalStateException.class, this.doorLock::lock);
    }

    @Test
    public void canBeLockedAfterSettingPin() {
        this.doorLock.setPin(random.nextInt(EXCLUSIVE_PIN_BOUND));
        this.doorLock.lock();
        assertTrue(this.doorLock.isLocked());
    }
}
