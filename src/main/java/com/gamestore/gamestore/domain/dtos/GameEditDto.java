package com.gamestore.gamestore.domain.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

import static com.gamestore.gamestore.constants.ErrorMessages.*;

public class GameEditDto {

    private long id;
    private BigDecimal price;
    private double size;

    public GameEditDto() {
    }

    public GameEditDto(long id, BigDecimal price, double size) {
        this.id = id;
        this.price = price;
        this.size = size;
    }

    @Min(value = 1, message = ID_INVALID)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DecimalMin(value = "0", message = PRICE_INVALID)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Min(value = 0, message = SIZE_INVALID)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
