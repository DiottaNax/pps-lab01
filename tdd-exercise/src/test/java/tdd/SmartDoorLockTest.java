package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockTest {
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
}
