package com.vipgroup.products.dataTransferObjects;

import lombok.Data;

@Data
public class CreateProductRequestDTO {
    String name;
    String description;
    String category;
    float price;

}