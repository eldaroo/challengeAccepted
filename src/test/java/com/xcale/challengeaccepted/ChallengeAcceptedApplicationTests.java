package com.xcale.challengeaccepted;

import com.xcale.challengeaccepted.constants.MessageConstants;
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
    private final String FAKE_CART_ID = "ee2fe7ff-8bca-4ebc-ba9c-c895e1480e76";
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
        ResponseEntity<Cart> creationResponse = cartController.createCart();
        ResponseEntity<Cart> responseEntity = cartController.removeCart(getCartId(creationResponse));
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(MessageConstants.CART_SUCCESS_REMOVE_MSG, responseEntity.getBody());
    }

    @Test
    public void testRemoveCartDoesNotExist() {
        ResponseEntity<Cart> responseEntity = cartController.removeCart(FAKE_CART_ID);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Invalid cart ID: 1", responseEntity.getBody());
    }

    @Test
    public void testAddProduct() {
        ResponseEntity<Cart> creationResponse = cartController.createCart();
        Product product = new Product();
        ResponseEntity<Cart> additionResponse = cartController.addProduct(getCartId(creationResponse), product);
        assertEquals(HttpStatus.ACCEPTED, additionResponse.getStatusCode());
        assertEquals(MessageConstants.PRODUCT_SUCCESS_ADD_MSG, additionResponse.getBody());
    }

    @Test
    public void testGetCartWithProducts() {
        ResponseEntity<Cart> creationResponse = cartController.createCart();
        Product product = new Product();
        product.setAmount(FAKE_AMOUNT);
        product.setDescription(FAKE_DESCRIPTION);
        product.setId(FAKE_ID);
        cartController.addProduct(getCartId(creationResponse), product);
        ResponseEntity<Cart> gettingResponse = cartController.getCart(getCartId(creationResponse));
        assertEquals(HttpStatus.ACCEPTED, gettingResponse.getStatusCode());
        assertEquals("products: {1=Product [id=1, description=I need all the products in black, amount=3]}",  getCartId(gettingResponse));
    }

    private String getCartId(ResponseEntity responseEntity) {
        return responseEntity.getBody().toString();
    }
}
