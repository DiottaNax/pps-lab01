package example.model;

public class BankAccountWithFee extends SimpleBankAccount {
    private static final double WITHDRAW_FEE = 1.0;

    public BankAccountWithFee(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    public static double getWithdrawFee() {
        return WITHDRAW_FEE;
    }

    /**
     * Allows the withdrawal of an amount from the account, if the given userID corresponds to the register holder ID
     * of the bank account.
     * Each withdraw detracts a fee from the bank account.
     */
    @Override
    public void withdraw(final int userID, final double amount) {
        super.withdraw(userID, amount + WITHDRAW_FEE);
    }
}
