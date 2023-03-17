package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.exception.InvalidCartIdException;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final int CLEAN_UP_EXPIRES_BG_TIME = 60000; // 10 minutes
    Map<Integer, Cart> carts = new HashMap<>();
    Integer uniqueId = 1;

    Integer getUniqueId() {
        return uniqueId++;
    }
    @Override
    public Integer createCart() {
        Integer cartId = getUniqueId();
        carts.put(cartId, new Cart());
        return cartId;
    }

    @Override
    public void addProduct(Integer cartId, Product product) throws InvalidCartIdException {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new InvalidCartIdException("Invalid cart ID: " + cartId);
        }
        cart.addProduct(product);
    }

    @Override
    public Cart getCartById(Integer cartId) throws InvalidCartIdException{
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new InvalidCartIdException("Invalid cart ID: " + cartId);
        }
        return cart;
    }

    @Override
    public void removeCart(Integer cartId) throws InvalidCartIdException {
        if (carts.remove(cartId) == null) {
            throw new InvalidCartIdException("Invalid cart ID: " + cartId);
        }
    }

    @Scheduled(fixedRate = CLEAN_UP_EXPIRES_BG_TIME)
    public void cleanUpExpiredCarts() {
        carts = carts.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isExpired())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
