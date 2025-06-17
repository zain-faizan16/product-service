package com.aion.productservice.service;

import com.aion.productservice.dto.ProductDTO;
import com.aion.productservice.dto.ProductResponseDTO;
import com.aion.productservice.entity.Product;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ProductService {

    Product create(ProductDTO dto);

    Product update(UUID id, ProductDTO dto);

    void delete(UUID id);

    Product getById(UUID id);

    ProductResponseDTO getAll(String name, String category, Boolean active, Pageable pageable);
}
