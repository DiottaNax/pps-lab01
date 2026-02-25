package example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static example.model.BankAccountTestUtils.depositSomeMoney;
import static example.model.BankAccountTestUtils.withdrawSomeMoney;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {
    private final Random random = new Random();
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi");
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    public void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    public void testDeposit() {
        final int depositAmount = 100;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }

    @Test
    public void testWrongDeposit() {
        final int otherAccountID = accountHolder.id() + 1;
        final double depositedAmount = depositSomeMoney(this.accountHolder.id(), this.bankAccount);
        depositSomeMoney(otherAccountID, this.bankAccount);
        assertEquals(depositedAmount, bankAccount.getBalance());
    }

    @Test
    public void testWithdraw() {
        final double depositedAmount = depositSomeMoney(this.accountHolder.id(), this.bankAccount);
        final double withdrewAmount = BankAccountTestUtils.withdrawSomeMoney(this.accountHolder.id(), this.bankAccount);
        assertEquals(depositedAmount - withdrewAmount, bankAccount.getBalance());
    }

    @Test
    public void testWrongWithdraw() {
        final int otherAccountID = accountHolder.id() + 1;
        final double depositedAmount = depositSomeMoney(this.accountHolder.id(), this.bankAccount);
        BankAccountTestUtils.withdrawSomeMoney(otherAccountID, this.bankAccount);
        assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
