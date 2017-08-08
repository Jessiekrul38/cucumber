package nl.quintor.demo.pineapples;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.java8.En;
import nl.quintor.controller.ShopController;
import nl.quintor.domain.Customer;
import nl.quintor.domain.Item;
import nl.quintor.domain.Shop;

public class PineappleStepDefs implements En {

    private Shop shop;
    private ShopController shopController;
    private Item pineapple;
    private Customer bob;

    public PineappleStepDefs() {

        Before(() -> {
            bob = new Customer();
            pineapple = new Item("Pineapple", 0.00);
            shop = new Shop();

            shopController = new ShopController(shop);
        });

        Given("^Bob has \\$(\\d+)\\.(\\d+)$", (Double balance) -> {
            bob.setBalance(balance);
        });

        And("^the shop has (\\d+) pineapples in stock$", (Integer amount) -> {
            shopController.fillStock(pineapple, amount);
        });

        And("^a pineapple costs \\$(\\d+)\\.(\\d+)$", (Double price) -> {
            pineapple.setPrice(price);
        });

        When("^Bob buys (\\d+) pineapple\\(s\\)$", (Integer amount) -> {
            shopController.sellItem(bob, pineapple, amount);
        });

        Then("^Bob has \\$(\\d+)\\.(\\d+) left$", (Double remainingBalance) -> {
            assertThat(bob.getBalance(), is(remainingBalance));
        });

        And("^the shop has (\\d+) pineapples left$", (Integer remainingAmount) -> {
            assertThat(shop.getStock().get(pineapple), is(remainingAmount));
        });

    }
}
