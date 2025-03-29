package com.halasa.microshop.catalog.api;

import java.util.List;
import java.util.UUID;

public record Product (
    UUID id,
    String name,
    String description,
    Double price,
    List<ProductCategory> categories
) {
}
