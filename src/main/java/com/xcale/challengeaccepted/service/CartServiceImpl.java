package com.xcale.challengeaccepted.service;

import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

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
    public void addProduct(Integer cartId, Product product) throws Exception {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new Exception("Cart not found: " + cartId);
        }
        cart.addProduct(product);
    }

    @Override
    public Cart getCartById(Integer cartId) {
        return carts.get(cartId);
    }

    @Override
    public void removeCart(Integer cartId) throws Exception {
        if (carts.remove(cartId) == null) {
            throw new Exception("Cart not found: " + cartId);
        }
    }

}
