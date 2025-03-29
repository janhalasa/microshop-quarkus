package com.halasa.microshop.shop.client;

import com.halasa.microshop.cart.api.Cart;
import com.halasa.microshop.cart.api.CartsEndpoint;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@RegisterRestClient(configKey = "cart-client")
public interface CartRestClient {

    @GET
    @Path(CartsEndpoint.Paths.GET_BY_ID)
    Cart getCartById(@PathParam(CartsEndpoint.Params.CART_ID) UUID cartId);
}
