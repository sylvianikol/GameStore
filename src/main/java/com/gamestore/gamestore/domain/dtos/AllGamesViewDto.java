package com.gamestore.gamestore.domain.dtos;

import java.math.BigDecimal;

public class AllGamesViewDto {

    private String title;
    private BigDecimal price;

    public AllGamesViewDto() {
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
