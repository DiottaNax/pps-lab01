package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}
