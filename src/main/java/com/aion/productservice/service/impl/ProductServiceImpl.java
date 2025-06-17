package com.aion.productservice.service.impl;

import com.aion.productservice.dto.ProductDTO;
import com.aion.productservice.dto.ProductResponseDTO;
import com.aion.productservice.entity.Product;
import com.aion.productservice.repository.ProductRepository;
import com.aion.productservice.service.ProductService;
import com.aion.productservice.utils.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository repo;

    @Override
    public Product create(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        Product saved = repo.save(product);
        log.info("Saved product with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public Product update(UUID id, ProductDTO dto) {
        Product product = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        BeanUtils.copyProperties(dto, product);
        product.setId(id);
        return repo.save(product);
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Override
    @Cacheable(value = "productById", key = "#id")
    public Product getById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductResponseDTO getAll(String name, String category, Boolean active, Pageable pageable) {
        Specification<Product> spec = ProductSpecification.hasName(name)
                .and(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.isActive(active));

        Page<Product> products = repo.findAll(spec, pageable);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductDetailResponseDTOS(products.stream().map(product -> modelMapper.map
                (product, ProductDTO.class)).collect(Collectors.toList()));
        productResponseDTO.setPageSize(Long.valueOf(products.getContent().size()));
        productResponseDTO.setPageNumber(Long.valueOf(products.getNumber()));
        productResponseDTO.setTotalPages(Long.valueOf(products.getTotalPages()));
        productResponseDTO.setTotalElements(Long.valueOf(products.getTotalElements()));
        return productResponseDTO;
    }
}

