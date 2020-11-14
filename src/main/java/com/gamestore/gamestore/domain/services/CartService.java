package com.gamestore.gamestore.domain.services;

import com.gamestore.gamestore.domain.dtos.BuyItemDto;

import java.util.List;

public interface CartService {

    String add(BuyItemDto buyItemDto);

    String remove(String title);

    String empty();

    boolean isCarEmpty();

    List<BuyItemDto> getCart();
}
