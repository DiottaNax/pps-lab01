package example.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the account holder concept.
 * That is: a person that can subscribe a bank account.
 * <p>
 * Each account holder has a name, a surname and an ID (unique in the bank system)
 */
public record AccountHolder(String name, String surname, int id) {
    private static final AtomicInteger INCREMENTAL_ID = new AtomicInteger();

    public AccountHolder(String name, String surname) {
        this(name, surname, INCREMENTAL_ID.getAndIncrement());
    }
}
