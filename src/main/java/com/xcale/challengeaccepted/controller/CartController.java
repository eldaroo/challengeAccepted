package com.xcale.challengeaccepted.controller;

import com.xcale.challengeaccepted.model.Product;
import com.xcale.challengeaccepted.service.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartServiceImpl service;

    public CartController(CartServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/{cartId}")
    public ResponseEntity addProduct(@PathVariable("cartId") Integer cartId, @RequestBody Product product) {
        try {
            service.addProduct(cartId, product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity getCartById(@PathVariable("cartId") Integer cartId) {
        try {
            return new ResponseEntity<>(service.getCartById(cartId), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createCart() {
        return new ResponseEntity<>(service.createCart(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity removeCart(@PathVariable("cartId") Integer cartId) {
        try {
            service.removeCart(cartId);
            return new ResponseEntity<>("The cart was removed successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
