import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.BankAccountWithFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountWithFeeTest {
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi");
        bankAccount = new BankAccountWithFee(accountHolder, 0);
    }

    @Test
    void shouldApplyFeeToWithdraws() {
        final int depositAmount = 100;
        final int withdrawAmount = 70;
        final double expectedBalance = depositAmount - withdrawAmount - BankAccountWithFee.getWithdrawFee();
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void shouldNotConsentWithdrawsWithoutEnoughBalance() {
        final int depositAmount = 100;
        bankAccount.deposit(accountHolder.id(), depositAmount);
        bankAccount.withdraw(accountHolder.id(), depositAmount);
        assertEquals(depositAmount, bankAccount.getBalance());
    }
}
