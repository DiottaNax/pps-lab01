package example.model;

import java.util.Random;

public class BankAccountTestUtils {
    private static final Random random = new Random();

    public static double depositSomeMoney(int id, BankAccount account) {
        double amount = random.nextDouble(1_000_000);
        account.deposit(id, amount);
        return amount;
    }

    public static double withdrawSomeMoney(int id, BankAccount account) {
        double amount = random.nextDouble(account.getBalance());
        account.withdraw(id, amount);
        return amount;
    }
}
