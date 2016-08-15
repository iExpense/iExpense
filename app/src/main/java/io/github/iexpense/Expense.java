package io.github.iexpense;

import java.util.ArrayList;

public class Expense {
    private Money money;
    private ArrayList<String> tags;  // sorted
    private long date;

    // Default constructor required for calls to DataSnapshot.getValue(Expense.class)
    public Expense() {
        this.money = new Money();
        this.tags = new ArrayList<>();
        this.date = System.currentTimeMillis();
    }

    public Expense(Money money, ArrayList<String> tags) {
        this.money = money;
        this.tags = tags;
        this.date = System.currentTimeMillis();
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
