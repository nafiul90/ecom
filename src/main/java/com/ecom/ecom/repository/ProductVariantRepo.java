package com.ecom.ecom.repository;

import com.ecom.ecom.model.ProductVariant;
import org.springframework.data.repository.CrudRepository;

public interface ProductVariantRepo extends CrudRepository<ProductVariant,Long> {
}
