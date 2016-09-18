package io.github.iexpense;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Expense {
    // expense types
    public static final int TYPE_BASE = 0;
    public static final int TYPE_TRANSFER = 1;
    public static final int TYPE_INCOME = 2;
    static int GLOBAL_SCALE = 2;

    // global parameters
    private static CurrencyUnit GlobalCurrencyUnit = CurrencyUnit.USD;

    // constants
    final int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;
    private Money money;
    private Set<Integer> tags;
    private long date;
    private int expenseType;

    // Default constructor required for calls to DataSnapshot.getValue(Expense.class)
    public Expense() {
        this.money = Money.zero(GlobalCurrencyUnit);
        this.tags = new HashSet<>();
        this.date = System.currentTimeMillis();
        this.expenseType = Expense.TYPE_BASE;
    }

    public Expense(Money money, Set<Integer> tags) {
        this.money = money;
        this.tags = tags;
        this.date = System.currentTimeMillis();
        this.expenseType = Expense.TYPE_BASE;
    }

    public Expense(Money money, Set<Integer> tags, int expenseType) {
        this.money = money;
        this.tags = tags;
        this.date = System.currentTimeMillis();
        this.expenseType = expenseType;
    }

    public static CurrencyUnit getGlobalCurrencyUnit() {
        return GlobalCurrencyUnit;
    }

    public static void setGlobalCurrencyUnit(CurrencyUnit globalCurrencyUnit) {
        GlobalCurrencyUnit = globalCurrencyUnit;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Set<Integer> getTags() {
        return tags;
    }

    public void setTags(Set<Integer> tags) {
        this.tags = tags;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(int expenseType) {
        this.expenseType = expenseType;
    }

    public void addTag(int tag) {
        this.tags.add(new Integer(tag));
    }

    public String getAmount() {
        return this.getMoney().getAmount().toString();
    }

    public void setAmount(String amount) {
        BigDecimal amount_to_add = new BigDecimal(amount);
        amount_to_add = amount_to_add.setScale(GLOBAL_SCALE, ROUNDING_MODE);
        this.setMoney(this.getMoney().plus(amount_to_add));
    }

    public CurrencyUnit getCurrencyUnit() {
        return this.getMoney().getCurrencyUnit();
    }

    public void setCurrencyUnit(CurrencyUnit currencyUnit) {
        this.setMoney(this.getMoney().withCurrencyUnit(currencyUnit));
    }

    public String toString() {
        return "" + this.getDate() + ", " + this.getExpenseType() + " - " +
                this.getCurrencyUnit() + this.getAmount() +
                " " + this.getTags();
    }

    public boolean valueEquals(Expense e) {
        return e.getExpenseType() == this.getExpenseType() &&
                e.getMoney().getCurrencyUnit().equals(this.getMoney().getCurrencyUnit()) &&
                e.getAmount().equals(this.getAmount()) &&
                e.getTags().equals(this.getTags());
    }
}
