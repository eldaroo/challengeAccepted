package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;

public interface CartService {
    Integer createCart();
    void addProduct(Integer cartId, Product product) throws Exception;
    Cart getCartById(Integer cartId);
    void removeCart(Integer cartId) throws Exception;
}
