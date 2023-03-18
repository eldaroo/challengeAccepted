package com.xcale.challengeaccepted;

import com.xcale.challengeaccepted.controller.CartController;
import com.xcale.challengeaccepted.model.Cart;
import com.xcale.challengeaccepted.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChallengeAcceptedApplicationTests {

    @Autowired
    private CartController cartController;
    private final Integer FAKE_ID = 1;
    private final String FAKE_DESCRIPTION = "I need all the products in black";
    private final Integer FAKE_AMOUNT = 3;

    @Test
    public void testCreateCart() {
        ResponseEntity<Cart> responseEntity = cartController.createCart();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testRemoveCartSuccessfully() {
        cartController.createCart();
        ResponseEntity<Cart> responseEntity = cartController.removeCart(FAKE_ID);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(MessageConstants.CART_SUCCESS_REMOVE_MSG, responseEntity.getBody());
    }

    @Test
    public void testRemoveCartDoesNotExist() {
        ResponseEntity<Cart> responseEntity = cartController.removeCart(FAKE_ID);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Invalid cart ID: 1", responseEntity.getBody());
    }

    @Test
    public void testAddProduct() {
        cartController.createCart();
        Product product = new Product();
        ResponseEntity<Cart> responseEntity = cartController.addProduct(FAKE_ID, product);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("The product was successfully added", responseEntity.getBody());
    }

    @Test
    public void testGetCartById() {
        cartController.createCart();
        Product product = new Product();
        product.setAmount(FAKE_AMOUNT);
        product.setDescription(FAKE_DESCRIPTION);
        product.setId(FAKE_ID);
        cartController.addProduct(FAKE_ID, product);
        ResponseEntity<Cart> responseEntity = cartController.getCartById(FAKE_ID);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("products: {1=Product [id=1, description=I need all the products in black, amount=3]}", responseEntity.getBody());
    }
}
