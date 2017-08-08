package nl.quintor.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private double balance;

    private Map<Item, Integer> items;

    public double getBalance() {
        return balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public Map<Item, Integer> getItems() {
        if (items == null) {
            this.items = new HashMap<Item, Integer>();
        }
        return items;
    }

    public boolean decreaseBalanceBy(final double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
