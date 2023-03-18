package com.xcale.challengeaccepted.model;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product productToAdd) {
        if (products.containsKey(productToAdd.getId())) {
            Product product = products.get(productToAdd.getId());
            product.setAmount(product.getAmount() + productToAdd.getAmount());
        } else {
            products.put(productToAdd.getId(), productToAdd);
        }
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "products: " + products;
    }

}
