package com.arul.bookstore.catalog.web.controller;

import com.arul.bookstore.catalog.ApplicationProperties;
import com.arul.bookstore.catalog.domain.ProductService;
import com.arul.bookstore.catalog.domain.model.PagedResult;
import com.arul.bookstore.catalog.domain.model.Product;
import com.arul.bookstore.catalog.web.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ApplicationProperties properties;

    @GetMapping
    PagedResult<Product> getProducts(
            @PageableDefault(size = 10, page = 0, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Fetching products for page: {}", pageable);

        return productService.getProducts(pageable);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
