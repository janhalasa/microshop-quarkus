package com.halasa.microshop;

import com.halasa.microshop.cart.api.Cart;
import com.halasa.microshop.cart.api.CartItem;
import com.halasa.microshop.cart.api.CartsEndpoint;
import com.halasa.microshop.shop.api.ShopResponse;
import com.halasa.microshop.shop.client.CartRestClient;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ShopMainPageRestControllerTest {

    @BeforeEach
    public void setUp() {
        CartRestClient cartRestClientMock = new CartRestClient() {

            @Override
            public Cart getCartById(UUID cartId) {
                return new Cart(
                        cartId,
                        List.of(
                                new CartItem(null, null, 121),
                                new CartItem(null, null, 343)
                        ),
                        OffsetDateTime.now());
            }
        };
        QuarkusMock.installMockForType(cartRestClientMock, CartRestClient.class, RestClient.LITERAL);
    }

    @Test
    void testHelloEndpoint() {
        final UUID cartId = UUID.randomUUID();

        final ShopResponse shopResponse = given()
                .log().all()
                .pathParam(CartsEndpoint.Params.CART_ID, cartId.toString())
                .when().get("/api/shop/{cartId}")
          .then()
                .log().all()
                .statusCode(200)
                .extract().as(ShopResponse.class);

        Assertions.assertNotNull(shopResponse);
        Assertions.assertEquals(cartId, shopResponse.cart().id());
        Assertions.assertEquals(2, shopResponse.cart().items().size());
        Assertions.assertTrue(shopResponse.cart().createdAt().isBefore(OffsetDateTime.now()));
    }

//    @Test
//    void testSse() throws URISyntaxException {
//        // https://medium.com/@korutx/implementing-tests-for-eventstream-endpoints-in-quarkus-a-journey-with-quarkus-sse-7e9e5c4f31c7
//        var client = RestClientBuilder.newBuilder()
//                .baseUri(new URI("http://localhost:8081/api/shop/{cartId}"))
//                .build(SseClient.class);
//
//        // Subscribe to the stream
//        Multi<SseEvent<Cart> eventStream = client.getCartById(UUID.randomUUID());
//        eventStream.subscribe().with(
//                event -> {
//                    if (event.data()
//                            .contains("\"eventType\":\"test\",\"tenantId\":\"testTenant\",\"data\":\"testData\"")) {
//                        eventReceived.set(true);
//                    }
//                },
//                throwable -> {
//                    System.err.println("Error in SSE stream: " + throwable.getMessage());
//                    throwable.printStackTrace();
//                },
//                () -> System.out.println("SSE stream completed")
//        );
//    }
}