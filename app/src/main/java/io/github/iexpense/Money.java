package io.github.iexpense;

import java.math.BigDecimal;
import java.util.Currency;

// This class handles money using the Java's Currency and
// BigDecimal class. It implements operations for adding
// amounts in two different currencies.
public class Money {
    private BigDecimal amount;
    private Currency currency;
}
