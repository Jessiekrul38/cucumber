package nl.quintor.domain;

import java.util.HashMap;
import java.util.Map;

public class Shop {

    private Map<Item, Integer> stock;

    public Map<Item, Integer> getStock() {
        if(stock == null) {
            this.stock = new HashMap<Item, Integer>();
        }
        return stock;
    }
}
