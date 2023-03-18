package com.xcale.challengeaccepted.controller;

import com.xcale.challengeaccepted.constants.MessageConstants;
import com.xcale.challengeaccepted.exception.InvalidIdException;
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
    public ResponseEntity addProduct(@PathVariable("cartId") String cartId, @RequestBody Product product) {
        try {
            service.addProduct(cartId, product);
            return new ResponseEntity<>("The product was successfully added", HttpStatus.ACCEPTED);
        } catch (InvalidCartIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity getCart(@PathVariable("cartId") String cartId) {
        try {
            return new ResponseEntity<>(service.getCartById(cartId).toString(), HttpStatus.ACCEPTED);
        } catch (InvalidCartIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity createCart() {
        return new ResponseEntity(service.createCart(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity removeCart(@PathVariable("cartId") String cartId) {
        try {
            service.removeCart(cartId);
            return new ResponseEntity<>("The cart was removed successfully", HttpStatus.ACCEPTED);
        } catch (InvalidCartIdException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
