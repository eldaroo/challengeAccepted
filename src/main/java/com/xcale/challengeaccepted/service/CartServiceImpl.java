package com.xcale.challengeaccepted.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xcale.challengeaccepted.constants.MessageConstants;
import com.xcale.challengeaccepted.exception.InvalidIdException;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements CartService {
    Cache<String, Cart> carts = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();

    String generateId() {
        return UUID.randomUUID().toString();
    }
    @Override
    public String createCart() {
        String cartId = generateId();
        carts.put(cartId, new Cart());
        return cartId;
    }

    @Override
    public void addProduct(String cartId, Product product) throws InvalidIdException {
        Cart cart = getCart(cartId);
        cart.addProduct(product);
    }

    @Override
    public Cart getCart(String cartId) throws InvalidIdException {
        Cart cart = carts.getIfPresent(cartId);
        if (cart == null) {
            throw new InvalidIdException(MessageConstants.INVALID_CART_ID_MSG + cartId);
        }
        return cart;
    }

    @Override
    public void removeCart(String cartId) throws InvalidIdException {
        carts.invalidate(cartId);
    }

}
