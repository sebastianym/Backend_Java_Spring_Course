package com.giweb.supply.business.primertallergiweb.model;

import lombok.Data;
import java.util.List;

@Data
public class Cart {
    private Long id;
    private Long userId;
    private String date;
    private List<CartProduct> products;

    @Data
    public static class CartProduct {
        private Long productId;
        private Integer quantity;
    }
}
