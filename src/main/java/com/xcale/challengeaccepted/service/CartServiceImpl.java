package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.exception.InvalidCartIdException;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements CartService {

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
            throw new InvalidCartIdException("Invalid cart ID: " + cartId);
        }
        return cart;
    }

    @Override
    public void removeCart(String cartId) throws InvalidIdException {
        carts.invalidate(getCart(cartId));
    }

    @Override
    @Scheduled(fixedRate = CLEAN_UP_EXPIRES_BG_TIME)
    public void cleanUpExpiredCarts() {
        carts = carts.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isExpired())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
