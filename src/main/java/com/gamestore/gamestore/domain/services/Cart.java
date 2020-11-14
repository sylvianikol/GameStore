package com.gamestore.gamestore.domain.services;

import com.gamestore.gamestore.domain.dtos.BuyItemDto;

import java.util.List;

public interface Cart {

    boolean contains(String title);

    void add(String title, BuyItemDto buyItemDto);

    void remove(String title);

    void empty();

    boolean isEmpty();

    List<BuyItemDto> getItems();
}

