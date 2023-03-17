package com.xcale.challengeaccepted.model;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Integer, Product> products = new HashMap<>();
    Integer uniqueId = 1;

    Integer getUniqueId() {
        return uniqueId++;
    }
    public void addProduct(Product product) {
        products.put(getUniqueId(), product);
    }
}
