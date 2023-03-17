package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.exception.InvalidCartIdException;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;

public interface CartService {
    Integer createCart();
    void addProduct(Integer cartId, Product product) throws InvalidCartIdException;
    Cart getCartById(Integer cartId) throws InvalidCartIdException;
    void removeCart(Integer cartId) throws InvalidCartIdException;
    void cleanUpExpiredCarts();
}
