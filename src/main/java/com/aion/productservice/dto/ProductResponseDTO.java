package com.aion.productservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDTO {
    private List<ProductDTO> productDetailResponseDTOS;
    private Long pageNumber;
    private Long pageSize;
    private Long totalElements;
    private Long totalPages;
}