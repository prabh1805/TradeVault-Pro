package com.tradevault.pro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "holdings")
public class Holding {
    @Id
    private String id;
    private String userId;
    private String symbol;
    private String exchange;

    private BigDecimal quantity;
    private BigDecimal averagePrice;

    private BigDecimal currentPrice;
    private BigDecimal pnl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
