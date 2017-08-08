package nl.quintor.demo.apples;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.quintor.controller.ShopController;
import nl.quintor.domain.Customer;
import nl.quintor.domain.Item;
import nl.quintor.domain.Shop;

public class MyStepdefs {

    private Shop shop;
    private ShopController shopController;
    private Item apple;
    private Customer bob;

    @Before
    public void before() {
        bob = new Customer();
        apple = new Item("Apple", 0.00);
        shop = new Shop();

        shopController = new ShopController(shop);
    }

    @Given("^Bob has \\$(\\d+\\.\\d+)$")
    public void bobHas$(final double balance) {
        bob.setBalance(balance);
    }

    @And("^the shop has (\\d+) apples in stock$")
    public void theShopHasApplesInStock(final int amount) {
        shopController.fillStock(apple, amount);
    }

    @And("^an apple costs \\$(.+)$")
    public void anAppleCosts$(final double price) {
        apple.setPrice(price);
    }

    @When("^Bob buys (\\d+) apple\\(s\\)$")
    public void bobBuysAppleSFor$(final int amount) {
        shopController.sellItem(bob, apple, amount);
    }

    @Then("^Bob has \\$(.+) left$")
    public void bobHas$Left(final double remainingBalance) {
        assertThat(bob.getBalance(), is(remainingBalance));
    }

    @And("^the shop has (\\d+) apples left$")
    public void theShopHasApplesLeft(final int remainingAmount) {
        assertThat(shop.getStock().get(apple), is(remainingAmount));
    }
}
