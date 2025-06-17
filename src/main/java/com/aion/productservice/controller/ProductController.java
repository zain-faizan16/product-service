package com.aion.productservice.controller;

import com.aion.productservice.dto.ApiResponse;
import com.aion.productservice.dto.ProductDTO;
import com.aion.productservice.dto.ProductResponseDTO;
import com.aion.productservice.entity.Product;
import com.aion.productservice.service.ProductService;
import com.aion.productservice.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(new ApiResponse<>(Constants.STATUS_CODE_CREATED, Constants.STATUS_SUCCESS_MESSAGE,
                productService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable UUID id, @RequestBody ProductDTO dto) {
        return new ResponseEntity<>(new ApiResponse<>(Constants.STATUS_CODE_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE,
                productService.update(id, dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable UUID id) {
        productService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>(Constants.STATUS_CODE_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE,
                null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable UUID id) {
        return new ResponseEntity<> (new ApiResponse<>(Constants.STATUS_CODE_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE,
                productService.getById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(new ApiResponse<>(Constants.STATUS_CODE_SUCCESS, Constants.STATUS_SUCCESS_MESSAGE,
                productService.getAll(name, category, active, pageable)), HttpStatus.OK);
    }
}

