package com.halasa.microshop.cart.rest;

import com.halasa.microshop.cart.api.Cart;
import com.halasa.microshop.cart.api.CartItem;
import com.halasa.microshop.cart.api.CartsEndpoint;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Path(CartsEndpoint.Paths.GET_BY_ID)
public class CartGetByIdRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cart getById(@PathParam(CartsEndpoint.Params.CART_ID) UUID cartUuid) {
        return new Cart(
                cartUuid,
                List.of(
                        new CartItem(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                1
                        ),
                        new CartItem(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                2
                        ),
                        new CartItem(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                3
                        )
                ),
                OffsetDateTime.now().minusMinutes(5)
        );
    }
}
