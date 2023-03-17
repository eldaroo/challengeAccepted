package com.xcale.challengeaccepted.model;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Integer, Product> products = new HashMap<>();
    private final long creationTime = System.currentTimeMillis();
    private static final long TTL = 10 * 60 * 10000; // 10 minutes in milliseconds
    Integer uniqueId = 1;

    Integer getUniqueId() {
        return uniqueId++;
    }
    public void addProduct(Product product) {
        products.put(getUniqueId(), product);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - creationTime > TTL;
    }
}
