package com.example.ecommerce.model;

import com.example.ecommerce.enums.ShipmentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Document
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private String userId;
    private List<Item> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private BigDecimal totalPrice;
    private ShipmentStatus shipmentStatus;

    public Order(String userId, List<Item> items) {
        this.userId = userId;
        this.items = items;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.totalPrice = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.shipmentStatus = ShipmentStatus.NEW;
    }
}
