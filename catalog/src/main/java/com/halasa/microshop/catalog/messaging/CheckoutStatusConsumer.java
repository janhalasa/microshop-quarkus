package com.halasa.microshop.catalog.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class CheckoutStatusConsumer {

    @Incoming("checkout-status")
    public void consume(CheckoutStatus checkoutStatus) {
        // process your price.
    }
}
