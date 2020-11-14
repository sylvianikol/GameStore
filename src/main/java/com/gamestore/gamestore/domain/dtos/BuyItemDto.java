package com.gamestore.gamestore.domain.dtos;

import java.math.BigDecimal;

public class BuyItemDto {

    private Long id;
    private String title;
    private BigDecimal price;

    public BuyItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
