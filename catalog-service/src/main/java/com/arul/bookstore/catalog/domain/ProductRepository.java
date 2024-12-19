package com.arul.bookstore.catalog.domain;

import com.arul.bookstore.catalog.domain.model.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByCode(String code);
}
