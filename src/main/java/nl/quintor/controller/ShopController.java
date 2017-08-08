package nl.quintor.controller;

import nl.quintor.domain.Customer;
import nl.quintor.domain.Item;
import nl.quintor.domain.Shop;
import nl.quintor.exception.InsufficientFundsException;

public class ShopController {

    private Shop shop;

    public ShopController(final Shop shop) {
        this.shop = shop;
    }

    public boolean sellItem(final Customer customer, final Item item, final int amount) {
        final double total = getTotal(item, amount);

        if (shop.getStock().containsKey(item)) {
            final int shopStock = shop.getStock().get(item);
            if (shopStock >= amount) {
                if (customer.decreaseBalanceBy(total)) {
                    customer.getItems().put(item, amount);
                    shop.getStock().put(item, shopStock - amount);
                    return true;
                } else {
                    throw new InsufficientFundsException(String.format("Customer has insufficient funds to buy %s!",
                        item.getName()));
                }
            } else {
                return sellItem(customer, item, shopStock);
            }
        }
        return false;
    }

    private double getTotal(final Item item, final int amount) {
        return item.getPrice() * amount;
    }

    public void fillStock(final Item item, final int amount) {
        shop.getStock().put(item, amount);
    }
}
