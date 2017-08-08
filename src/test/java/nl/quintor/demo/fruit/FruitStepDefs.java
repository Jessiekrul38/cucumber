package nl.quintor.demo.fruit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.quintor.controller.ShopController;
import nl.quintor.domain.Customer;
import nl.quintor.domain.Item;
import nl.quintor.domain.Shop;
import nl.quintor.model.CustomItem;

public class FruitStepDefs {

    private Map<String, Item> items;
    private Shop shop;
    private ShopController shopController;
    private Customer bob;

    @Before
    public void before() {
        bob = new Customer();
        shop = new Shop();
        items = new HashMap<String, Item>();
        shopController = new ShopController(shop);
    }

    @After
    public void after() {
    }

    @Given("^Bob has \\$(\\d+\\.\\d+)$")
    public void bobHas$(final double balance) {
        bob.setBalance(balance);
    }

    @When("^Bob buys (\\d+) (.*)\\(s\\)$")
    public void bobBuysFruitSFor$(final int amount, final String fruit) {
        shopController.sellItem(bob, getItemByName(fruit), amount);
    }

    @Then("^Bob has \\$(.+) left$")
    public void bobHas$Left(final double remainingBalance) {
        assertThat(bob.getBalance(), is(remainingBalance));
    }

    @And("^the shop has (\\d+) (.*) left$")
    public void theShopHasFruitLeft(final int remainingAmount, final String fruit) {
        assertThat(shop.getStock().get(getItemByName(fruit)), is(remainingAmount));
    }

    @And("^the shop has the following items in stock$")
    public void theShopHasTheFollowingItemsInStock(final List<CustomItem> customItems) {
        for (final CustomItem customItem : customItems) {
            final Item item = new Item(customItem.getName(), customItem.getPrice());
            items.put(item.getName(), item);
            shopController.fillStock(item, customItem.getAmount());
        }
    }

    private Item getItemByName(final String name) {
        final Item item = items.get(name);
        if (item == null) {
            fail();
        }
        return item;
    }
}
