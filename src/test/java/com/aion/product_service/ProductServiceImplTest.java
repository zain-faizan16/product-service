package com.aion.productservice;

import com.aion.productservice.dto.ProductDTO;
import com.aion.productservice.dto.ProductResponseDTO;
import com.aion.productservice.entity.Product;
import com.aion.productservice.repository.ProductRepository;
import com.aion.productservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;
    private UUID productId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productId = UUID.randomUUID();

        productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setCategory("Electronics");
        productDTO.setActive(true);

        product = new Product();
        product.setId(productId);
        product.setName("Laptop");
        product.setCategory("Electronics");
        product.setActive(true);
    }

    @Test
    void testCreate() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.create(productDTO);

        assertNotNull(created);
        assertEquals("Laptop", created.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateSuccess() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updated = productService.update(productId, productDTO);

        assertNotNull(updated);
        assertEquals(productId, updated.getId());
        verify(productRepository).findById(productId);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateNotFound() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.update(productId, productDTO));
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).deleteById(productId);

        productService.delete(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testGetByIdSuccess() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product found = productService.getById(productId);

        assertNotNull(found);
        assertEquals(productId, found.getId());
        verify(productRepository).findById(productId);
    }

    @Test
    void testGetByIdNotFound() {
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.getById(productId));
        assertEquals("Product not found", exception.getMessage());
    }

}

