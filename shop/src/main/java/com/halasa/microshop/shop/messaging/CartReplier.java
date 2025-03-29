package com.halasa.microshop.shop.messaging;

import com.halasa.microshop.cart.api.Cart;
import com.halasa.microshop.cart.api.CartItem;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CartReplier {

    @Incoming("cart-in")
    @Outgoing("cart-replies-out")
    public Uni<Cart> cartReplyProducer(UUID cartId) {
        Log.info("Replying to cartId " + cartId);
        return Uni.createFrom()
                .item(new Cart(
                        cartId,
                        List.of(new CartItem(null, null, 3)),
                        OffsetDateTime.now()))
                .onItem()
                .delayIt().by(Duration.ofSeconds(2));
    }
}
