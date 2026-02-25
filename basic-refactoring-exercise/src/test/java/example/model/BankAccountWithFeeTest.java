package example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static example.model.BankAccountTestUtils.depositSomeMoney;
import static example.model.BankAccountTestUtils.withdrawSomeMoney;
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
        final double depositAmount = depositSomeMoney(this.accountHolder.id(), this.bankAccount);
        final double withdrewAmount = withdrawSomeMoney(this.accountHolder.id(), this.bankAccount);
        final double expectedBalance = depositAmount - withdrewAmount - BankAccountWithFee.getWithdrawFee();
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void shouldNotConsentWithdrawsWithoutEnoughBalance() {
        final double depositedAmount = depositSomeMoney(this.accountHolder.id(), this.bankAccount);
        bankAccount.withdraw(accountHolder.id(), depositedAmount);
        assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
