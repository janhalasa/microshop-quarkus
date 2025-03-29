package com.halasa.microshop.catalog.api;

import java.util.UUID;

public record ProductCategory(
        UUID id,
        String name
) {
}
