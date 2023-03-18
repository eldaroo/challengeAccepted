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
    private final String FAKE_DESCRIPTION = "Fake Product";
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
        assertEquals("Invalid cart ID: "+FAKE_CART_ID, responseEntity.getBody());
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
        Product product = getProduct();
        Cart expected = new Cart();
        expected.addProduct(product);

        String cartId = createCarWith(product);
        validateExpected(expected, cartId);
    }

    @Test
    public void testAddExistingProduct(){
        Product product = getProduct();

        Cart expected = new Cart();
        expected.addProduct(product);
        expected.addProduct(product);

        product.setAmount(FAKE_AMOUNT*2);
        String cartId = createCarWith(product);

        assertEquals(1, expected.getProducts().size());
        assertEquals(FAKE_AMOUNT*2, expected.getProducts().values().stream().findFirst().get().getAmount());
        validateExpected(expected, cartId);
    }

    private void validateExpected(Cart expected, String cartId) {
        ResponseEntity<Cart> gettingResponse = cartController.getCart(cartId);
        assertEquals(HttpStatus.ACCEPTED, gettingResponse.getStatusCode());
        assertEquals(expected,  gettingResponse.getBody());
    }

    private String createCarWith(Product product) {
        ResponseEntity<Cart> creationResponse = cartController.createCart();
        String cartId = getCartId(creationResponse);
        cartController.addProduct(cartId, product);
        return cartId;
    }

    private Product getProduct() {
        Product product = new Product();
        product.setAmount(FAKE_AMOUNT);
        product.setDescription(FAKE_DESCRIPTION);
        product.setId(FAKE_ID);
        return product;
    }

    private String getCartId(ResponseEntity responseEntity) {
        return responseEntity.getBody().toString();
    }
}
