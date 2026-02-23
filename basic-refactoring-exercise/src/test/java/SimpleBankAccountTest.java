import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {
    private static final double WITHDRAW__FEE = 1;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi");
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        final int depositAmount = 100;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int otherAccountId = accountHolder.id() + 1;
        final int depositAmount = 100;
        final int depositAmountOnOtherAccount = 50;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.deposit(otherAccountId, depositAmountOnOtherAccount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int depositAmount = 100;
        final int withdrawAmount = 70;
        final double expectedBalanceAfterWithdraw = depositAmount - withdrawAmount - WITHDRAW__FEE;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(expectedBalanceAfterWithdraw, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final int otherAccountId = accountHolder.id() + 1;
        final int depositAmount = 100;
        final int otherAccountWithdrawAmount = 70;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.withdraw(otherAccountId, otherAccountWithdrawAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }
}
