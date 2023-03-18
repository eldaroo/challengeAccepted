package com.xcale.challengeaccepted.model;
import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equal(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }
}
