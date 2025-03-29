package com.halasa.microshop.cart.api;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Cart (
        UUID id,
        List<CartItem> items,
        OffsetDateTime createdAt
) {
}
