package com.giweb.supply.business.primertallergiweb.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
