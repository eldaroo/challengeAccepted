# challengeAccepted

ChallengeAccepted
These are the main endpoints of the excercise proposed by XCale

#POST
**add a product**
```http://localhost:8080/cart/{cartId}```

Body: (json)
```{
    "id": 3,
    "description": "I want them all in black",
    "amount":3
}
```

#POST
**create a Cart**
```http://localhost:8080/cart```

#GET
**get a cart by Id**
```http://localhost:8080/cart/{cartId}```

#DELETE
**remove a cart by Id**
```http://localhost:8080/cart/{cartId}```
