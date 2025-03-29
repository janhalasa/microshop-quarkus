package com.halasa.microshop.shop.rest;

import com.halasa.microshop.cart.api.Cart;
import com.halasa.microshop.shop.api.ShopResponse;
import com.halasa.microshop.shop.client.CartRestClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.reply.KafkaRequestReply;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestMulti;
import org.jboss.resteasy.reactive.RestStreamElementType;

import java.util.UUID;

@Path("/shop")
public class ShopMainPageRestController {

    private final KafkaRequestReply<UUID, Cart> cartRequestReply;
    private final CartRestClient cartRestClient;

    public ShopMainPageRestController(
            @Channel("cart") KafkaRequestReply<UUID, Cart> cartRequestReply,
            @RestClient CartRestClient cartRestClient) {
        this.cartRequestReply = cartRequestReply;
        this.cartRestClient = cartRestClient;
    }

    @Path("/{cartId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ShopResponse> getCartById(@PathParam("cartId") String cartId) {
//        return cartRequestReply.request(UUID.fromString(cartId))
//                .map(ShopResponse::new);
        return Uni.createFrom().item(new ShopResponse(cartRestClient.getCartById(UUID.fromString(cartId))));
    }

    @Path("/sse/{cartId}")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<ShopResponse> streamCartById(@PathParam("cartId") String cartId) {
        return RestMulti.fromMultiData(Multi.createFrom()
                .item(new ShopResponse(cartRestClient.getCartById(UUID.fromString(cartId)))))
                .encodeAsJsonArray(false)
                .build();
    }
}
