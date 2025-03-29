package com.halasa.microshop.cart.api;

import java.util.UUID;

public record CartItem(
        UUID id,
        UUID productId,
        Integer count
) {
}
