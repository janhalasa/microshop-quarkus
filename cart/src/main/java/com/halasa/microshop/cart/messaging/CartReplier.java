package com.halasa.microshop.cart.messaging;

import com.halasa.microshop.cart.api.Cart;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CartReplier {

    @Incoming("cart")
    @Outgoing("cart-reply")
    public Cart handleCartRequest(UUID cartId) {
        return new Cart(cartId, List.of(), OffsetDateTime.now());
    }
}
