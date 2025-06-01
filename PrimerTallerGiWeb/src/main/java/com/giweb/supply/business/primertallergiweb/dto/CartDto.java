package com.giweb.supply.business.primertallergiweb.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<CartProductDto> products;
    private Double total; // Nuevo campo para el total del carrito

    @Data
    public static class CartProductDto {
        private Long productId;
        private Integer quantity;
        private ProductDto product;
    }
}
