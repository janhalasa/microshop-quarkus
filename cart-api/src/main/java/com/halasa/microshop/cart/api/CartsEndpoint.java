package com.halasa.microshop.cart.api;

public interface CartsEndpoint {

    interface Params {
        String CART_ID = "cartId";
    }

    interface Paths {
        String BASE = "/carts";
        String GET_BY_ID = BASE + "/{" + Params.CART_ID + "}";
    }
}
