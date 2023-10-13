package com.example.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
@NoArgsConstructor
public class Item {
    @Id
    private String id;
    private String itemName;
    private String itemDescription;
    private int  quantity;
    private BigDecimal price;

    public Item(String itemName, String itemDescription, int quantity, BigDecimal price) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
    }
}
