package com.halasa.microshop.stock.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckoutStatusConsumer {

    @Incoming("checkout-status")
    public void consume(CheckoutStatus checkoutStatus) {
        // process your price.
    }
}
