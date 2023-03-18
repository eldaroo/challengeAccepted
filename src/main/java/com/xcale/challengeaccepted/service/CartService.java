package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.exception.InvalidIdException;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;

public interface CartService {
    String createCart();
    Cart getCart(String cartId) throws InvalidIdException;

    void removeCart(String cartId) throws InvalidIdException;

    void addProduct(String cartId, Product product) throws InvalidIdException;

}
